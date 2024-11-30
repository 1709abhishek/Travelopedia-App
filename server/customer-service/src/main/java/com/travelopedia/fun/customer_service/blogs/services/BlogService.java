package com.travelopedia.fun.customer_service.blogs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelopedia.fun.customer_service.blogs.models.Blog;
import com.travelopedia.fun.customer_service.blogs.repositories.BlogRepository;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    public Blog updateBlog(int blogId, Blog updatedBlog) {
        Optional<Blog> blogOptional = blogRepository.findById(blogId);
        if (blogOptional.isPresent()) {
            Blog blog = blogOptional.get();
            blog.setTitle(updatedBlog.getTitle());
            blog.setContent(updatedBlog.getContent());
            blog.setTags(updatedBlog.getTags());
            blog.setUpdatedAt(new java.util.Date());
            return blogRepository.save(blog);
        }
        throw new RuntimeException("Blog not found");
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public Blog getBlogById(int blogId) {
        Optional<Blog> blogOptional = blogRepository.findById(blogId);
        if (blogOptional.isPresent()) {
            return blogOptional.get();
        }
        throw new RuntimeException("Blog not found");
    }

    public List<Blog> getBlogsByUserId(int userId) {
        return blogRepository.findByUserId(userId);
    }

    public void deleteBlog(int blogId) {
        blogRepository.deleteById(blogId);
    }


}
