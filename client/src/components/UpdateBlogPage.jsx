import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import Header from "./Header";
import Footer from "./Footer";
import "../styles/updateblogpage.css";
import { getStoredToken } from "../services/CustomerServices";

const UpdateBlogPage = () => {
  const { blogId } = useParams();
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [tags, setTags] = useState([]);
  const [image, setImage] = useState(null);
  const navigate = useNavigate();

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
        setTitle(data.title);
        setContent(data.content);
        setTags(JSON.parse(data.tags));
        setImage(data.image);
      } catch (error) {
        console.error("Error fetching blog details:", error);
      }
    };
    fetchBlogDetails();
  }, [blogId]);

  const handleTagInput = (e) => {
    if (e.key === "Enter" && e.target.value.trim()) {
      setTags([...tags, e.target.value.trim()]);
      e.target.value = "";
      e.preventDefault();
    }
  };

  const handleTagRemove = (index) => {
    setTags(tags.filter((_, i) => i !== index));
  };

  const handleImageChange = (e) => {
    setImage(e.target.files[0]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const token = getStoredToken();
    const formData = new FormData();
    formData.append("title", title);
    formData.append("content", content);
    formData.append("tags", JSON.stringify(tags));
    if (image) {
      formData.append("image", image);
    }

    try {
      const response = await fetch(`http://localhost:8080/blogs/update/${blogId}`, {
        method: "PUT",
        headers: {
          "Authorization": `Bearer ${token}`
        },
        body: formData
      });

      if (response.ok) {
        navigate(`/blogs/${blogId}`);
      } else {
        console.error("Failed to update blog");
      }
    } catch (error) {
      console.error("Error updating blog:", error);
    }
  };

  return (
    <div className="update-blog-page">
      <Header />
      <div className="content">
        <h2>Update your blog</h2>
        <form onSubmit={handleSubmit} className="update-blog-form">
          <div className="form-group">
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
            <textarea
              id="content"
              placeholder="Tell about your last trip..."
              value={content}
              onChange={(e) => setContent(e.target.value)}
              required
            ></textarea>
          </div>
          <div className="form-group">
            <input
              type="text"
              id="tags"
              placeholder="Enter a tag and press Enter"
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
          <div className="form-group">
            <label htmlFor="image" className="image-label">
              {image ? image.name : "Share an image"}
            </label>
            <input
              type="file"
              id="image"
              onChange={handleImageChange}
              style={{ display: "none" }}
            />
          </div>
          <button type="submit" className="update-blog-button">Update Blog</button>
        </form>
      </div>
      <Footer />
    </div>
  );
};

export default UpdateBlogPage;