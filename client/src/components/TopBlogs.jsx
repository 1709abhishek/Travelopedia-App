import React from "react";
import { Link } from "react-router-dom";
import "../styles/topblogs.css";
import l1 from "../assets/l1.jpg";
import l2 from "../assets/l2.jpg";
import l3 from "../assets/l3.jpg";
import l4 from "../assets/l4.jpg";
import l5 from "../assets/l5.jpg";

const topBlogs = [
  {
    id: 1,
    title: "Exploring the Beaches of Thailand",
    image: l1,
  },
  {
    id: 2,
    title: "A Journey Through the Mountains of Nepal",
    image: l2,
  },
  {
    id: 3,
    title: "Discovering the Wonders of Italy",
    image: l3,
  },
  {
    id: 4,
    title: "Adventures in the Australian Outback",
    image: l4,
  },
  {
    id: 5,
    title: "Cultural Experiences in Japan",
    image: l5,
  },
];

const TopBlogs = () => {
  return (
    <div className="top-blogs">
      <h2>Top Stories</h2>
      <div className="top-blogs-list">
        {topBlogs.map((blog) => (
          <div key={blog.id} className="top-blog-item">
            <img src={blog.image} alt={blog.title} className="top-blog-image" />
            <div className="top-blog-text">
              <Link to={`/blog/${blog.id}`} className="top-blog-link">
                <h3>{blog.title}</h3>
              </Link>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default TopBlogs;