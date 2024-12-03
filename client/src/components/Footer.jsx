import React from 'react';
import { Link } from 'react-router-dom';
import "../styles/footer.css"; 

const Footer = () => {
  return (
    <section className="footer">
      <div className="foot">
        <div className="footer-content">
          <div className="footlinks">
            <h4>Quick Links</h4>
            <ul>
              <li>
                <Link to="/signup">Sign Up</Link>
              </li>
              <li>
                <Link to="/contact-us">About Us</Link>
              </li>
              <li>
                <Link to="/contact-us">Contact Us</Link>
              </li>
            </ul>
          </div>

          <div className="footlinks">
            <h4>Connect</h4>
            <div className="social">
              <a href="#" target="_blank">
                <i className="bx bxl-facebook"></i>
              </a>
            </div>
          </div>
        </div>
      </div>

      <div className="end">
        <p>Copyright © 2024 Travelopedia</p>
      </div>
    </section>
  );
};

export default Footer;