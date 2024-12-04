import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import Header from "./Header";
import Footer from "./Footer";
import TopBlogs from "./TopBlogs";
import "../styles/blogpage.css";
import { getStoredToken } from "../services/CustomerServices";
import defaultImage from "../assets/default-image.jpg";

const BlogPage = () => {
  const [blogs, setBlogs] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");

  useEffect(() => {
    // Fetch blogs
    const fetchBlogs = async () => {
      try {
        const token = getStoredToken();
        const response = await fetch("http://localhost:8080/blogs/all", {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
          }
        });
        const data = await response.json();
        setBlogs(data);
      } catch (error) {
        console.error("Error fetching blogs:", error);
      }
    };
    fetchBlogs();
  }, []);

  const handleSearch = (e) => {
    setSearchTerm(e.target.value);
  };

  const filteredBlogs = blogs.filter((blog) => {
    const tags = JSON.parse(blog.tags).join(" ").toLowerCase();
    return (
      blog.title.toLowerCase().includes(searchTerm.toLowerCase()) ||
      tags.includes(searchTerm.toLowerCase())
    );
  });

  return (
    <div className="blog-page">
      <Header />
      <div className="content">
      <div className="main-content">
        <div className="search-bar">
          <input
            type="text"
            placeholder="Search blogs..."
            value={searchTerm}
            onChange={handleSearch}
          />
          <Link to="/create-blog" className="create-blog-button">
            Write ✒️ 
          </Link>
        </div>
        <div className="blog-list">
          {filteredBlogs.map((blog) => (
            <Link to={`/blogs/${blog.blogId}`} className="blog-link">
            <div key={blog.id} className="blog-item">
              <div className="blog-text">
              
                <h3 className="blog-title">
                    
                      {blog.title}
                    
                </h3>
                
                <p className="blog-description">{blog.content.substring(0, 100)}...</p>
                
                <p className="blog-date">
                  {new Date(blog.createdAt).toLocaleDateString("en-US", {
                    year: "numeric",
                    month: "long",
                    day: "numeric"
                  })}
                </p>
                <div className="blog-tags">
                  {JSON.parse(blog.tags).map((tag, index) => (
                    <span key={index} className="blog-tag">{tag}</span>
                  ))}
                </div>
              </div>
              <img src={defaultImage} alt="Blog" className="blog-image" />
            </div>
            </Link>
          ))}
        </div>
      </div>
      <TopBlogs />
      </div>
      <Footer />
    </div>
  );
};

export default BlogPage;