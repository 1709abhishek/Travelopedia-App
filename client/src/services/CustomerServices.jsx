import axios from 'axios';
import serviceConfig from './Config';

console.log(serviceConfig.host);

export const signUpService = async (name, email, password) => {
  return await axios({
    method: 'post',
    url: `${serviceConfig.host}/accounts/register`,
    data: {
      name: name,
      email: email,
      password: password
    }
  });
}

export const signInService = async (email, password) => {
  return await axios({
    method: 'post',
    url: `${serviceConfig.host}/accounts/login`,
    data: {
      email: email,
      password: password
    }
  });
}

export const getStoredToken = () => {
  return sessionStorage.getItem("token");
};

export const getStoredUserId = () => {
  return sessionStorage.getItem("id");
};

export const getStoredEmail = () => {
  return sessionStorage.getItem("email");
};