package com.travelopedia.fun.recommendation_service;

import com.travelopedia.fun.recommendation_service.model.ChatMessage;
import com.travelopedia.fun.recommendation_service.repository.ChatMessageRepository;
import com.travelopedia.fun.recommendation_service.service.ChatServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link ChatServiceImpl} class.
 * This test class validates the behavior of the methods in the {@link ChatServiceImpl} service layer,
 * particularly in the context of interacting with the {@link ChatMessageRepository} to fetch and save
 * chat messages.
 * 
 * The class uses Mockito to mock the repository dependency and test the service logic in isolation.
 */
@ExtendWith(MockitoExtension.class)
public class ChatServiceImplTest {

    /**
     * Mocked repository that simulates interaction with the data source for {@link ChatMessage} entities.
     */
    @Mock
    private ChatMessageRepository chatMessageRepository;

    /**
     * The instance of the service under test, which is injected with the mocked repository.
     */
    @InjectMocks
    private ChatServiceImpl chatServiceImpl;

    /**
     * A sample chat message used in the tests.
     */
    private ChatMessage chatMessage;

    /**
     * Sets up the test environment, initializing the {@link ChatMessage} object before each test.
     * This method is executed before each test method to ensure a fresh instance of {@link ChatMessage}.
     */
    @BeforeEach
    void setUp() {
        chatMessage = new ChatMessage();
        chatMessage.setId(1L);
        chatMessage.setContent("Hello Travelopedia!");
        chatMessage.setConversationId("test-conversation-id");
    }

    /**
     * Tests the {@link ChatServiceImpl#getChatMessagesByConversationId(String)} method.
     * This test simulates the case where the repository returns a list of chat messages for the provided
     * conversation ID, and checks if the service correctly retrieves and returns the messages.
     */
    @Test
    void testGetChatMessagesByConversationId() {
        when(chatMessageRepository.findByConversationId(anyString()))
                .thenReturn(Collections.singletonList(chatMessage));

        List<ChatMessage> chatMessages = chatServiceImpl.getChatMessagesByConversationId("test-conversation-id");

        assertEquals(1, chatMessages.size());
        assertEquals("Hello Travelopedia!", chatMessages.get(0).getContent());
    }

    /**
     * Tests the {@link ChatServiceImpl#getChatMessagesByConversationId(String)} method when no messages are found.
     * This test ensures that the service correctly handles the case when no messages exist for the specified
     * conversation ID.
     */
    @Test
    void testGetChatMessagesByConversationId_NoMessagesFound() {
        when(chatMessageRepository.findByConversationId(anyString()))
                .thenReturn(Collections.emptyList());
        
        List<ChatMessage> chatMessages = chatServiceImpl.getChatMessagesByConversationId("non-existing-conversation-id");

        assertEquals(0, chatMessages.size());
    }

    /**
     * Tests the {@link ChatServiceImpl#addMessage(ChatMessage)} method.
     * This test simulates saving a new chat message to the repository and ensures that the service correctly
     * saves and returns the message.
     */
    @Test
    void testAddMessage() {
        when(chatMessageRepository.save(any(ChatMessage.class)))
                .thenReturn(chatMessage);

        ChatMessage savedMessage = chatServiceImpl.addMessage(chatMessage);

        assertEquals(chatMessage.getContent(), savedMessage.getContent());

        verify(chatMessageRepository, times(1)).save(any(ChatMessage.class));
    }

    /**
     * Tests the {@link ChatServiceImpl#deleteChat(String)} method.
     * This test verifies that the service correctly delegates the deletion of chat messages to the repository
     * based on the provided conversation ID.
     */
    @Test
    void testDeleteChat() {
        chatServiceImpl.deleteChat("test-conversation-id");

        verify(chatMessageRepository, times(1)).deleteByConversationId("test-conversation-id");
    }
}
