import axios from 'axios';
import serviceConfig from './Config';

console.log(serviceConfig.host);

export const signUpService = async (firstName, lastName, email, password) => {
  return await axios({
    method: 'post',
    url: `${serviceConfig.host}/accounts/register`,
    data: {
      firstName: firstName,
      lastName: lastName,
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

export const logOutService = async (jwt) => {
  return await axios({
    method: 'post',
    url: `${serviceConfig.host}/accounts/logout`,
    headers: {
      'Authorization': `Bearer ${jwt}`
    }
  });
}

export const getUserDetailsService = async (email) => {
  email = email.trim().replace(/^["']|["']$/g, '');
  return await axios({
    method: 'get',
    url: `${serviceConfig.host}/profile/${email}`
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