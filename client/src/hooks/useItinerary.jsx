import { useState } from 'react';
import ItineraryService from '../services/ItineraryService';

export const useItinerary = () => {
  const [refreshFlag, setRefreshFlag] = useState(false);

  // Get all itineraries
  const getAllItineraries = async () => {
    try {
      const data = await ItineraryService.getAllItineraries();
      return data;
    } catch (error) {
      console.error('Error fetching all itineraries', error);
      return null;
    }
  };

  // Get itinerary by ID
  const getItineraryById = async (itineraryId,tripId) => {
    try {
      const data = await ItineraryService.getItineraryById(itineraryId,tripId);
      return data;
    } catch (error) {
      console.error('Error fetching itinerary by ID', error);
      return null;
    }
  };

  // Get itineraries by trip ID
  const getItinerariesByTripId = async (tripId) => {
    try {
      const data = await ItineraryService.getItinerariesByTripId(tripId);
      return data || []; 
    } catch (error) {
      console.error('Error fetching itineraries by trip ID', error);
      return [];
    }
  };

  // Create a new itinerary
  const createItinerary = async (tripId, itinerary) => {
    try {
      const data = await ItineraryService.createItinerary(tripId, itinerary);
      return data;
    } catch (error) {
      console.error('Error creating itinerary', error);
      return null;
    }
  };

  // Update an itinerary
  const updateItinerary = async (id, tripId, itinerary) => {
    try {
      const data = await ItineraryService.updateItinerary(id, tripId, itinerary);
      return data;
    } catch (error) {
      console.error('Error updating itinerary', error);
      return null;
    }
  };

  // Delete an itinerary
  const deleteItinerary = async (itinerayId, tripId) => {
    try {
      const data = await ItineraryService.deleteItinerary(itinerayId, tripId);
      return data;
    } catch (error) {
      console.error('Error deleting itinerary', error);
      return null;
    }
  };

  const refreshItineraries = () => {
    setRefreshFlag(!refreshFlag);
  };

  return {
    getAllItineraries,
    getItineraryById,
    getItinerariesByTripId,
    createItinerary,
    updateItinerary,
    deleteItinerary,
    refreshItineraries,
    refreshFlag
  };
};
