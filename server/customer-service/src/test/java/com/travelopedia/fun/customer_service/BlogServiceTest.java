package com.travelopedia.fun.customer_service;

import com.travelopedia.fun.customer_service.accounts.models.Account;
import com.travelopedia.fun.customer_service.accounts.repository.AccountsRepository;
import com.travelopedia.fun.customer_service.blogs.models.Blog;
import com.travelopedia.fun.customer_service.blogs.models.BlogDTO;
import com.travelopedia.fun.customer_service.blogs.repositories.BlogRepository;
import com.travelopedia.fun.customer_service.blogs.services.BlogService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

/**
 * Unit tests for the {@link BlogService} class.
 * This class tests the methods of the {@link BlogService} related to blog creation, updating, deletion,
 * retrieval, and interaction with the repository layer. 
 * It ensures that the service behaves as expected when interacting with {@link BlogRepository} and {@link AccountsRepository}.
 * The tests mock dependencies like the repositories and account data to focus on the business logic.
 */
@ExtendWith(MockitoExtension.class)
public class BlogServiceTest {

    /**
     * The instance of {@link BlogService} under test, injected with mocked dependencies.
     */
    @InjectMocks
    private BlogService blogService;

    /**
     * Mocked repository for blog data.
     */
    @Mock
    private BlogRepository blogRepository;

    /**
     * Mocked repository for account data.
     */
    @Mock
    private AccountsRepository accountsRepository;

    /**
     * A sample account used in the tests to simulate the blog's author.
     */
    private Account account;

    /**
     * A sample blog used in the tests for creating, updating, and deleting blog entries.
     */
    private Blog blog;

    /**
     * Initializes the test data before each test.
     * This method sets up an account and a blog object to be used across different test cases.
     */
    @BeforeEach
    public void setupAccount() {
        account = new Account();
        account.setFirstName("Deep");
        account.setLastName("Shah");
        account.setEmail("depShah@pfw.edu");
        account.setPassword("password123");
    }

    /**
     * Initializes the blog object before each test.
     * This method sets up a blog object to be used for testing blog creation, update, and deletion.
     */
    @BeforeEach
    public void setupBlog() {
        blog = new Blog();
        blog.setTitle("Gujarat Blog");
        blog.setContent("I love Gujarat");
        blog.setTags("test, blog");
        blog.setUser(account);
    }

    /**
     * Tests the {@link BlogService#createBlog(Blog, String)} method for successful blog creation.
     * This test ensures that the blog is created and associated with the correct account, and that the
     * blog is saved to the repository.
     */
    @Test
    public void testCreateBlog_Success() {
        when(accountsRepository.findByEmail(account.getEmail())).thenReturn(account);
        blogService.createBlog(blog, account.getEmail());

        verify(blogRepository, times(1)).save(blog);

        assertEquals(account, blog.getUser());
    }

    /**
     * Tests the {@link BlogService#createBlog(Blog, String)} method to verify behavior when the account is found.
     * This test ensures that the blog is saved when the account exists in the system.
     */
    @Test
    public void testCreateBlog_AccountFound() {
        when(accountsRepository.findByEmail(account.getEmail())).thenReturn(account);
        blogService.createBlog(blog, account.getEmail());

        verify(blogRepository, times(1)).save(blog);
    }

    /**
     * Tests the {@link BlogService#getAllBlogs()} method.
     * This test verifies that the method correctly interacts with the blog repository to fetch all blogs.
     */
    @Test
    public void testGetAllBlogs() {
        when(blogRepository.findAll()).thenReturn(java.util.Collections.emptyList());
        blogService.getAllBlogs();

        verify(blogRepository, times(1)).findAll();
    }

    /**
     * Tests the {@link BlogService#getBlogById(int)} method.
     * This test ensures that the service correctly fetches a blog by its ID and maps it to a {@link BlogDTO}.
     */
    @Test
    public void testGetBlogById() {
        when(blogRepository.findById(anyInt())).thenReturn(Optional.of(blog));

        BlogDTO fetchedBlog = blogService.getBlogById(blog.getBlogId());

        assertNotNull(fetchedBlog);
        assertEquals(blog.getBlogId(), fetchedBlog.getBlogId());
        assertEquals(blog.getTitle(), fetchedBlog.getTitle());
        assertEquals(blog.getContent(), fetchedBlog.getContent());

        verify(blogRepository, times(1)).findById(blog.getBlogId());
    }

    /**
     * Tests the {@link BlogService#deleteBlog(int, String)} method for deleting a blog by ID.
     * This test ensures that the blog is deleted from the repository when it exists.
     */
    @Test
    public void testDeleteBlogById() {
        when(blogRepository.findById(anyInt())).thenReturn(Optional.of(blog));
        blogService.deleteBlog(0, account.getEmail());

        verify(blogRepository, times(1)).deleteById(0);
    }

    /**
     * Tests the {@link BlogService#updateBlog(int, Blog, String)} method for updating an existing blog.
     * This test verifies that the blog is updated correctly and returns the updated {@link BlogDTO}.
     */
    @Test
    public void testUpdateBlog() {
        Blog existingBlog = new Blog();
        existingBlog.setBlogId(1);
        existingBlog.setTitle("title");
        existingBlog.setContent("content");
        existingBlog.setTags("tag1, tag2");
        existingBlog.setUser(account);
        existingBlog.setImage("image".getBytes());

        when(blogRepository.findById(1)).thenReturn(Optional.of(existingBlog));
        when(blogRepository.save(any(Blog.class))).thenReturn(existingBlog);

        Blog updatedBlog = new Blog();
        updatedBlog.setTitle("new title");
        updatedBlog.setContent("new content");
        updatedBlog.setTags("tag1, tag2, tag3");
        updatedBlog.setImage("new image".getBytes());

        BlogDTO updatedBlogDTO = blogService.updateBlog(1, updatedBlog, account.getEmail());

        assertNotNull(updatedBlogDTO);
        assertEquals("new title", updatedBlogDTO.getTitle());
        assertEquals("new content", updatedBlogDTO.getContent());
        assertArrayEquals("new image".getBytes(), updatedBlogDTO.getImage());
        assertEquals(1L, updatedBlogDTO.getBlogId());
        assertNotNull(updatedBlogDTO.getUpdatedAt());
    }
}
