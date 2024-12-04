import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Header from "./Header";
import Footer from "./Footer";
import "../styles/createblogpage.css";
import { getStoredToken } from "../services/CustomerServices";

const CreateBlog = () => {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [tags, setTags] = useState([]);
  const [tagInput, setTagInput] = useState("");
  const navigate = useNavigate();

  const handleTagInput = (e) => {
    if (e.key === "Enter" && tagInput.trim()) {
      setTags([...tags, tagInput.trim()]);
      setTagInput("");
      e.preventDefault();
    }
  };

  const handleTagRemove = (index) => {
    setTags(tags.filter((_, i) => i !== index));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const token = getStoredToken();
    const blogData = {
      title,
      content,
      tags: JSON.stringify(tags),
      createdAt: new Date().toISOString(),
      updatedAt: new Date().toISOString()
    };

    try {
      const response = await fetch("http://localhost:8080/blogs/create", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify(blogData)
      });

      if (response.ok) {
        const newBlog = await response.json();
        navigate(`/blogs/${newBlog.blogId}`);
      } else {
        console.error("Failed to create blog");
      }
    } catch (error) {
      console.error("Error creating blog:", error);
    }
  };

  return (
    <div className="create-blog-page">
      <Header />
      <div className="content">
        <h2>Share your experience</h2>
        <form onSubmit={handleSubmit} className="create-blog-form">
          <div className="form-group">
            {/* <label htmlFor="title">Title</label> */}
            <input
              type="text"
              id="title"
              placeholder="Title"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
              required
            />
          </div>
          <div className="form-group">
            {/* <label htmlFor="content">Content</label> */}
            <textarea
              id="content"
              placeholder="Tell about your last trip..."
              value={content}
              onChange={(e) => setContent(e.target.value)}
              required
            ></textarea>
          </div>
          <div className="form-group">
            {/* <label htmlFor="tags">Tags (comma separated)</label> */}
            <input
              type="text"
              id="tags"
              placeholder="Enter a tag and press Enter"
              value={tagInput}
              onChange={(e) => setTagInput(e.target.value)}
              onKeyDown={handleTagInput}
            />
            <div className="tags-container">
              {tags.map((tag, index) => (
                <span key={index} className="tag">
                  {tag}
                  <button type="button" onClick={() => handleTagRemove(index)}>
                    &times;
                  </button>
                </span>
              ))}
            </div>
          </div>
          <button type="submit" className="create-blog-button">Create Blog</button>
        </form>
      </div>
      <Footer />
    </div>
  );
};

export default CreateBlog;