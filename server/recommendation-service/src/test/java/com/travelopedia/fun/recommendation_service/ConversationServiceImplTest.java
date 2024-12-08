package com.travelopedia.fun.recommendation_service;

import com.travelopedia.fun.recommendation_service.model.Conversation;
import com.travelopedia.fun.recommendation_service.repository.ConversationRepository;
import com.travelopedia.fun.recommendation_service.service.ConversationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConversationServiceImplTest {

    @Mock
    private ConversationRepository conversationRepository;

    @InjectMocks
    private ConversationServiceImpl conversationServiceImpl;

    private Conversation conversation;

    @BeforeEach
    void setUp() {
        conversation = new Conversation();
        conversation.setConversationId("test-conversation-id");
        conversation.setEmail("FtXQx@example.com");
        conversation.setConversationName("Test Conversation");
    }

    @Test
    void testCreateConversation() {
        when(conversationRepository.save(any(Conversation.class))).thenReturn(conversation);
        Conversation createdConversation = conversationServiceImpl.createConversation(conversation.getEmail(), conversation.getConversationName());
        ArgumentCaptor<Conversation> argumentCaptor = ArgumentCaptor.forClass(Conversation.class);
        verify(conversationRepository, times(1)).save(argumentCaptor.capture());

        Conversation capturedConversation = argumentCaptor.getValue();
        assertNotNull(capturedConversation);
        assertEquals("Test Conversation", capturedConversation.getConversationName());
        assertEquals("FtXQx@example.com", capturedConversation.getEmail());
        assertNotNull(capturedConversation.getConversationId());
        assertFalse(capturedConversation.getConversationId().isEmpty());
    }

    @Test
    void testGetConversation() {
        when(conversationRepository.findByConversationId("test-conversation-id"))
                .thenReturn(Optional.of(conversation));

        Conversation fetchedConversation = conversationServiceImpl.getConversation("test-conversation-id");
         assertNotNull(fetchedConversation);
        assertEquals(conversation.getConversationId(), fetchedConversation.getConversationId());
        verify(conversationRepository, times(1)).findByConversationId("test-conversation-id");
    }

    @Test
    void testGetConversationsByEmail() {
        when(conversationRepository.findByEmail("FtXQx@example.com"))
                .thenReturn(Arrays.asList(conversation));

        List<Conversation> conversations = conversationServiceImpl.getConversationsByEmail("FtXQx@example.com");
        assertNotNull(conversations);
        assertFalse(conversations.isEmpty());
        assertEquals(1, conversations.size());
        assertEquals("Test Conversation", conversations.get(0).getConversationName());
        verify(conversationRepository, times(1)).findByEmail("FtXQx@example.com");
    }

    @Test
    void testUpdateConversation() {
        when(conversationRepository.findByConversationId("test-conversation-id"))
                .thenReturn(Optional.of(conversation));

        when(conversationRepository.save(any(Conversation.class))).thenAnswer(invocation -> {
            Conversation updatedConversation = invocation.getArgument(0);
            updatedConversation.setConversationName("Updated Conversation");
            return updatedConversation;
        });

        Conversation updatedConversation = conversationServiceImpl.updateConversation("test-conversation-id", "Updated Conversation");

        assertNotNull(updatedConversation);
        assertEquals("Updated Conversation", updatedConversation.getConversationName());
        verify(conversationRepository, times(1)).save(updatedConversation);
    }

    @Test
    void testDeleteConversation() {
        when(conversationRepository.findByConversationId("test-conversation-id"))
                .thenReturn(Optional.of(conversation));

        conversationServiceImpl.deleteConversation("test-conversation-id");
        verify(conversationRepository, times(1)).delete(conversation);
    }

    @Test
    void testGetConversationNotFound() {
        when(conversationRepository.findByConversationId("non-existing-id"))
                .thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            conversationServiceImpl.getConversation("non-existing-id");
        });

        assertEquals("Conversation not found", thrown.getMessage());
        verify(conversationRepository, times(1)).findByConversationId("non-existing-id");
    }

    @Test
    void testUpdateConversationNotFound() {
        when(conversationRepository.findByConversationId("non-existing-id"))
                .thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            conversationServiceImpl.updateConversation("non-existing-id", "Updated Name");
        });

        assertEquals("Conversation not found", thrown.getMessage());
        verify(conversationRepository, times(1)).findByConversationId("non-existing-id");
    }

    @Test
    void testDeleteConversationNotFound() {
       when(conversationRepository.findByConversationId("non-existing-id"))
                .thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            conversationServiceImpl.deleteConversation("non-existing-id");
        });
        assertEquals("Conversation not found", thrown.getMessage());
        verify(conversationRepository, times(1)).findByConversationId("non-existing-id");
    }
}
