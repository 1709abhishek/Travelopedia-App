import axios from 'axios';
import serviceConfig from './Config';

const API_URL = '/api/trips';

class TripService {
  // Create a new trip
  createTrip = async (data) => {
    try {
      const response = await axios.post(`${serviceConfig.itineraryHost}${API_URL}`, data);
      return response.data;
    } catch (error) {
      console.error('Error creating trip:', error);
      throw error;
    }
  };

  // Update an existing trip
  updateTrip = async (tripId, data) => {
    try {
      const url = `${serviceConfig.itineraryHost}${API_URL}/updateTrip/${tripId}`;
      const response = await axios.put(url, data);
      return response.data;
    } catch (error) {
      console.error('Error updating trip:', error);
      throw error;
    }
  };

  // Delete a trip by ID
  deleteTrip = async (tripId) => {
    try {
      const response = await axios.delete(`${serviceConfig.itineraryHost}${API_URL}/deleteTrip/${tripId}`);
      return response.data;
    } catch (error) {
      console.error('Error deleting trip:', error);
      throw error;
    }
  };

  // Get all trips
  getAllTrips = async () => {
    try {
      const response = await axios.get(`${serviceConfig.itineraryHost}${API_URL}`);
      return response.data;
    } catch (error) {
      console.error('Error fetching all trips:', error);
      throw error;
    }
  };

  // Get a specific trip by ID
  getTripById = async (tripId) => {
    try {
      const response = await axios.get(`${serviceConfig.itineraryHost}${API_URL}/getTripById/${tripId}`);
      return response.data;
    } catch (error) {
      console.error(`Error fetching trip by ID (${tripId}):`, error);
      throw error;
    }
  };

  // Get a trip with itineraries and activities by tripId
  getTripWithItineraries = async (tripId) => {
    try {
      const response = await axios.get(`${serviceConfig.itineraryHost}${API_URL}/with-itineraries-activities/${tripId}`);
      return response.data;
    } catch (error) {
      console.error(`Error fetching trip with itineraries and activities for trip ID ${tripId}:`, error);
      throw error;
    }
  };
}

export default new TripService();
