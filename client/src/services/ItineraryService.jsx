import axios from 'axios';
import serviceConfig from './Config';
const API_URL = '/api/itineraries';

class ItineraryService {
  // Get all itineraries
  getAllItineraries = async () => {
    try {
      const response = await axios.get(API_URL);
      return response.data;
    } catch (error) {
      console.error('Error fetching itineraries', error);
      throw error;
    }
  };

  // Get itinerary by itinerayId and tripId
  getItineraryById = async (itineraryId,tripId) => {
    try {
      const response = await axios.get(`${serviceConfig.itineraryHost}${API_URL}/${itineraryId}?tripId=${tripId}`);
      return response.data;
    } catch (error) {
      console.error('Error fetching itinerary by ID', error);
      throw error;
    }
  };

  getItinerariesByTripId = async (tripId) => {
    try {
        console.log(`Calling API: ${serviceConfig.itineraryHost}${API_URL}/trip/${tripId}`);
        const response = await axios.get(`${serviceConfig.itineraryHost}${API_URL}/trip/${tripId}`);
        return response.data; 
      }catch (error) {
      console.error('Error fetching itineraries for trip', error);
    }
  };
  
  createItinerary = async (tripId, itinerary) => {
    try {
      const requestBody = {
        activities: itinerary.activities
      };
      const response = await axios.post(`${serviceConfig.itineraryHost}${API_URL}?tripId=${tripId}`, requestBody);
      return response.data;
    } catch (error) {
      console.error('Error creating itinerary', error);
      throw error;
    }
  };

  // Update an itinerary
  updateItinerary = async (itineraryId, tripId, itinerary) => {
    try {
      const response = await axios.put(`${serviceConfig.itineraryHost}${API_URL}/${itineraryId}?tripId=${tripId}`, itinerary);
      return response.data;
    } catch (error) {
      console.error('Error updating itinerary', error);
      throw error;
    }
  };

  // Delete an itinerary
  deleteItinerary = async (itinerayId, tripId) => {
    try {
      const response = await axios.delete(`${serviceConfig.itineraryHost}${API_URL}/${itinerayId}?tripId=${tripId}`);
      return response.data;
    } catch (error) {
      console.error('Error deleting itinerary', error);
      throw error;
    }
  };
}

export default new ItineraryService();
