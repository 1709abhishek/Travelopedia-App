package com.travelopedia.fun.customer_service.blogs.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelopedia.fun.customer_service.accounts.models.Account;
import com.travelopedia.fun.customer_service.blogs.models.Blog;
import com.travelopedia.fun.customer_service.blogs.models.BlogDTO;
import com.travelopedia.fun.customer_service.blogs.repositories.BlogRepository;
import com.travelopedia.fun.customer_service.accounts.repository.AccountsRepository;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private AccountsRepository accountsRepository;

    public Blog createBlog(Blog blog, String username) {

        // Fetch the user's details based on the username
        Account account = accountsRepository.findByEmail(username);

        // Set the userId field in the Blog object
        blog.setUser(account);
        blog.setTags(String.join(", ", blog.getTags().split(","))); // Convert tags array to comma-separated string
        blog.setCreatedAt(Date.from(Instant.now())); 
        blog.setUpdatedAt(Date.from(Instant.now()));
        return blogRepository.save(blog);
    }

    public BlogDTO updateBlog(int blogId, Blog updatedBlog, String username) {
        Optional<Blog> blogOptional = blogRepository.findById(blogId);
        if (blogOptional.isPresent()) {
            Blog blog = blogOptional.get();
            if (!blog.getUser().getEmail().equals(username)) {
                throw new RuntimeException("You do not have permission to update this blog");
            }

            blog.setTitle(updatedBlog.getTitle());
            blog.setContent(updatedBlog.getContent());
            blog.setTags(updatedBlog.getTags());
            blog.setUpdatedAt(new Date());
            Blog newupdatedBlog = blogRepository.save(blog);

            // Convert Blog entity to BlogDTO
            BlogDTO blogDTO = new BlogDTO();
            blogDTO.setBlogId((long) newupdatedBlog.getBlogId());
            blogDTO.setTitle(newupdatedBlog.getTitle());
            blogDTO.setContent(newupdatedBlog.getContent());
            blogDTO.setTags(newupdatedBlog.getTags());
            blogDTO.setCreatedAt(newupdatedBlog.getCreatedAt());
            blogDTO.setUpdatedAt(newupdatedBlog.getUpdatedAt());
            return blogDTO;
        }
        throw new RuntimeException("Blog not found");
    }

    public List<BlogDTO> getAllBlogs() {
        List<Blog> blogs = blogRepository.findAll();
        return blogs.stream().map(blog -> {
            BlogDTO dto = new BlogDTO();
            dto.setBlogId((long) blog.getBlogId());
            dto.setTitle(blog.getTitle());
            dto.setContent(blog.getContent());
            dto.setTags(blog.getTags());
            dto.setCreatedAt(blog.getCreatedAt());
            dto.setUpdatedAt(blog.getUpdatedAt());
            return dto;
        }).collect(Collectors.toList());
    }

    public BlogDTO getBlogById(int blogId) {
        Optional<Blog> blogOptional = blogRepository.findById(blogId);
        if (blogOptional.isPresent()) {
            Blog blog = blogOptional.get();
            BlogDTO dto = new BlogDTO();
            dto.setBlogId((long) blog.getBlogId());
            dto.setTitle(blog.getTitle());
            dto.setContent(blog.getContent());
            dto.setTags(blog.getTags());
            dto.setCreatedAt(blog.getCreatedAt());
            dto.setUpdatedAt(blog.getUpdatedAt());
            return dto;
        }
        throw new RuntimeException("Blog not found");
    }

    public List<BlogDTO> getBlogsByUserId(int userId) {
        List<Blog> blogs = blogRepository.findByUserId(userId);
        return blogs.stream().map(blog -> {
            BlogDTO dto = new BlogDTO();
            dto.setBlogId((long) blog.getBlogId());
            dto.setTitle(blog.getTitle());
            dto.setContent(blog.getContent());
            dto.setTags(blog.getTags());
            dto.setCreatedAt(blog.getCreatedAt());
            dto.setUpdatedAt(blog.getUpdatedAt());
            return dto;
        }).collect(Collectors.toList());
    }

    public void deleteBlog(int blogId, String username) {
        Optional<Blog> blogOptional = blogRepository.findById(blogId);
        if (blogOptional.isPresent()) {
            Blog blog = blogOptional.get();

            // Check if the blog belongs to the current user
            if (!blog.getUser().getEmail().equals(username)) {
                throw new RuntimeException("You do not have permission to delete this blog");
            }
            blogRepository.deleteById(blogId);
        }
    }


}
