import axios from 'axios';
import serviceConfig from './Config';
const API_URL = '/api/activities';

class ActivityService {

  // Get activities by itinerary ID
  getActivitiesByItineraryId = async (itineraryId) => {
    try {
      const response = await axios.get(`${API_URL}/itinerary/${itineraryId}`);
      return response.data;
    } catch (error) {
      console.error('Error fetching activities for itinerary', error);
      throw error;
    }
  };

  // Get activity by ID
  getActivityById = async (activityId) => {
    try {
      const response = await axios.get(`${API_URL}/${activityId}`);
      return response.data;
    } catch (error) {
      console.error('Error fetching activity by ID', error);
      throw error;
    }
  };

  // Create a new activity
  createActivity = async (itineraryId, activity) => {
    try {
      const response = await axios.post(`${serviceConfig.itineraryHost}${API_URL}?itineraryId=${itineraryId}`, activity);
      return response.data;
    } catch (error) {
      console.error('Error creating activity', error);
      throw error;
    }
  };

  // Update an existing activity
  updateActivity = async (tripId, itineraryId, activityId, activity) => {
    try {
      const response = await axios.put(`${serviceConfig.itineraryHost}${API_URL}/update/${activityId}?tripId=${tripId}&itineraryId=${itineraryId}`, activity);
      return response.data;
    } catch (error) {
      console.error('Error updating activity', error);
      throw error;
    }
  };

  // Delete an activity
  deleteActivity = async (tripId, itineraryId, activityId) => {
    try {
      const response = await axios.delete(`${serviceConfig.itineraryHost}${API_URL}/delete/${activityId}?tripId=${tripId}&itineraryId=${itineraryId}`);
      return response.data;
    } catch (error) {
      console.error('Error deleting activity', error);
      throw error;
    }
  };
}

export default new ActivityService();
