import { AccountCircle, Lock } from "@mui/icons-material";
import React, { useState } from "react";
import { useNavigate } from 'react-router-dom';

import travelPic from "../assets/travel_vertical.jpg";
import { useAuth } from "../contexts/AuthContext";
import { signInService } from "../services/CustomerServices";
import { useAccount } from "../contexts/AccountContext";
import "../styles/loginpage.css";

export default function SignIn() {
  const navigate = useNavigate ();

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const { fetchProfileDetails } = useAccount();
  const {login} = useAuth();

  const handleSignIn = async (e) => {
    e.preventDefault();
    try {
      const response = await signInService(username, password);
      const { jwt, userId, email } = response.data;
      sessionStorage.setItem('token', jwt);
      sessionStorage.setItem('id', userId);
      sessionStorage.setItem('email', email);
      login(jwt, username);
      fetchProfileDetails();
      navigate('/');
    } catch (error) {
      if (error.response) {
        setError("Login failed: Bad credentials");
      } else if (error.request) {
        setError("Login failed: Unable to connect to the server");
      } else {
        setError("Login failed: An unexpected error occurred");
      }
    }
  };

  return (
    <div className="sign-in-background">
      <div className="app-name-background">Travelopedia</div>
      <div className="form-modal__wrapper">
        <div className="sign-up">
          <img src={travelPic} alt="Camels in the desert" />
        </div>
        <div className="sign-up__container">
          <form onSubmit={handleSignIn} className="sign-up__form">
            <h2 className="login-title">Sign in</h2>
            <div className="input-field">
              <AccountCircle style={{ fontSize: 30, color: "#999" }} />
              <input
                type="email"
                placeholder="Email"
                required
                value={username}
                onChange={(e) => setUsername(e.target.value)}
              />
            </div>
            <div className="input-field">
              <Lock style={{ fontSize: 30, color: "#999" }} />
              <input
                type="password"
                placeholder="Password"
                required
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </div>
            <input type="submit" value="Login" className="btn" />
            <div className="error-message">{error && <p>{error}</p>} </div>
            <p>
              Don't have an account?{" "}
              <a href="/signup" className="account-text" id="sign-up-link">
                Sign up
              </a>
            </p>
          </form>
        </div>
      </div>
    </div>
  );
}
