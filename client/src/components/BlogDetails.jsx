import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import Header from "./Header";
import Footer from "./Footer";
import TopBlogs from "./TopBlogs";
import "../styles/blogdetails.css";
import { getStoredToken } from "../services/CustomerServices";
import defaultImage from "../assets/default-image.jpg";

const BlogDetails = () => {
  const { blogId } = useParams();
  const [blog, setBlog] = useState(null);

  useEffect(() => {
    // Fetch blog details
    const fetchBlogDetails = async () => {
      try {
        const token = getStoredToken();
        const response = await fetch(`http://localhost:8080/blogs/${blogId}`, {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
          }
        });
        const data = await response.json();
        setBlog(data);
      } catch (error) {
        console.error("Error fetching blog details:", error);
      }
    };
    fetchBlogDetails();
  }, [blogId]);

  const getTimeDifference = (date) => {
    const now = new Date();
    const blogDate = new Date(date);
    const diffInSeconds = Math.floor((now - blogDate) / 1000);

    const intervals = [
      { label: "year", seconds: 31536000 },
      { label: "month", seconds: 2592000 },
      { label: "day", seconds: 86400 },
      { label: "hour", seconds: 3600 },
      { label: "minute", seconds: 60 },
      { label: "second", seconds: 1 },
    ];

    for (const interval of intervals) {
      const count = Math.floor(diffInSeconds / interval.seconds);
      if (count >= 1) {
        return count === 1
          ? `1 ${interval.label} ago`
          : `${count} ${interval.label}s ago`;
      }
    }

    return "just now";
  };

  if (!blog) {
    return <div>Loading...</div>;
  }

  const getImageSrc = (image) => {
    if (image) {
      console.log("image", image);
      return `data:image/jpeg;base64,${image}`;
    }
    return defaultImage;
  };

  if (!blog) {
    return <div>Loading...</div>;
  }

  return (
    <div className="blog-details-page">
      <Header />
      <div className="content">
        <div className="main-content">
          <div className="blog-details">
          <img src={getImageSrc(blog.image)} alt={blog.title} className="blog-details-image" />
            <h1 className="blog-details-title">{blog.title}</h1>
            <p className="blog-details-user">By {blog.userName}</p>
            <p className="blog-details-date">{getTimeDifference(blog.createdAt)}</p>
            <p className="blog-details-content">{blog.content}</p>
            <div className="blog-details-tags">
              {JSON.parse(blog.tags).map((tag, index) => (
                <span key={index} className="blog-tag">{tag}</span>
              ))}
            </div>
          </div>
        </div>
        <TopBlogs />
      </div>
      <Footer />
    </div>
  );
};

export default BlogDetails;