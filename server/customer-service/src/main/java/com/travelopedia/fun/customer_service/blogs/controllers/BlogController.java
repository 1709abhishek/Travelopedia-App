package com.travelopedia.fun.customer_service.blogs.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelopedia.fun.customer_service.blogs.models.Blog;
import com.travelopedia.fun.customer_service.blogs.models.BlogDTO;
import com.travelopedia.fun.customer_service.blogs.services.BlogService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/create")
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
        Blog createdBlog = blogService.createBlog(blog);
        return ResponseEntity.ok(createdBlog);
    }

    @PostMapping("/update/{blogId}")
    public ResponseEntity<Blog> updateBlog(@PathVariable int blogId, @RequestBody Blog updatedBlog) {
        Blog blog = blogService.updateBlog(blogId, updatedBlog);
        return ResponseEntity.ok(blog);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllBlogs(HttpServletRequest request) {

        String jwtStatus = (String) request.getAttribute("jwtStatus");
        if ("invalid".equals(jwtStatus)) {
            System.out.println("Invalid JWT token");
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body("Invalid JWT token");
        }
        
        System.out.println("Control flow in getAllBlogs method");
        List<Blog> blogs = blogService.getAllBlogs();
        System.out.println("Size of blogs: " + blogs.size());
        for (Blog blog : blogs) {
            System.out.println(blog);
        }
        List<BlogDTO> blogDTOs = blogs.stream().map(blog -> {
            BlogDTO dto = new BlogDTO();
            dto.setBlogId((long)blog.getBlogId());
            dto.setTitle(blog.getTitle());
            dto.setContent(blog.getContent());
            dto.setTags(blog.getTags());
            dto.setCreatedAt(blog.getCreatedAt());
            dto.setUpdatedAt(blog.getUpdatedAt());
            return dto;
        }).collect(Collectors.toList());
        // return ResponseEntity.ok("All blogs");
        return ResponseEntity.ok(blogDTOs);
    }

    @GetMapping("/{blogId}")
    public ResponseEntity<?> getBlogById(@PathVariable int blogId, HttpServletRequest request) {
        System.out.println("Blog ID: " + blogId);
        // return ResponseEntity.ok("Blog ID: " + blogId);
        String jwtStatus = (String) request.getAttribute("jwtStatus");
        if ("invalid".equals(jwtStatus)) {
            System.out.println("Invalid JWT token");
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body("Invalid JWT token");
        }

        Blog blog = blogService.getBlogById(blogId);
        if (blog == null) {
            return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body("Blog not found");
        }

        BlogDTO dto = new BlogDTO();
        dto.setBlogId((long) blog.getBlogId());
        dto.setTitle(blog.getTitle());
        dto.setContent(blog.getContent());
        dto.setTags(blog.getTags());
        dto.setCreatedAt(blog.getCreatedAt());
        dto.setUpdatedAt(blog.getUpdatedAt());

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Blog>> getBlogsByUserId(@PathVariable int userId) {
        List<Blog> blogs = blogService.getBlogsByUserId(userId);
        return ResponseEntity.ok(blogs);
    }

    @DeleteMapping("/delete/{blogId}")
    public ResponseEntity<String> deleteBlog(@PathVariable int blogId) {
        blogService.deleteBlog(blogId);
        return ResponseEntity.ok("Blog deleted successfully");
    }
}
