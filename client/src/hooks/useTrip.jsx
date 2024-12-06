import { useState, useEffect } from 'react';
import TripService from '../services/TripService';

export const useTrip = () => {
  const [trips, setTrips] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [refreshFlag, setRefreshFlag] = useState(false);

  // Fetch all trips
  const getAllTrips = async () => {
    setLoading(true);
    setError(null); 
    try {
      const data = await TripService.getAllTrips();
      setTrips(data);
    } catch (error) {
      setError('Error fetching trips');
    } finally {
      setLoading(false);
    }
  };

  // Fetch a trip by ID
  const getTripById = async (tripId) => {
    setLoading(true);
    setError(null);
    try {
      const data = await TripService.getTripById(tripId);
      return data;
    } catch (error) {
      setError(`Error fetching trip by ID (${tripId})`);
    } finally {
      setLoading(false);
    }
  };

  // Fetch a trip with itineraries and activities by trip ID
  const getTripWithItineraries = async (tripId) => {
    setLoading(true);
    setError(null);
    try {
      const data = await TripService.getTripWithItineraries(tripId);
      return data;
    } catch (error) {
      setError(`Error fetching trip with itineraries and activities for trip ID ${tripId}`);
    } finally {
      setLoading(false);
    }
  };

  // Create a new trip
  const createTrip = async (data) => {
    setLoading(true);
    setError(null);
    try {
      const newTrip = await TripService.createTrip(data);
      setRefreshFlag(!refreshFlag); 
      return newTrip;
    } catch (error) {
      setError('Error creating trip');
    } finally {
      setLoading(false);
    }
  };

  // Update an existing trip
  const updateTrip = async (tripId, data) => {
    setLoading(true);
    setError(null);
    try {
      const updatedTrip = await TripService.updateTrip(tripId, data);
      setRefreshFlag(!refreshFlag); 
      return updatedTrip;
    } catch (error) {
      setError('Error updating trip');
    } finally {
      setLoading(false);
    }
  };

  // Delete a trip
  const deleteTrip = async (tripId) => {
    setLoading(true);
    setError(null);
    try {
      const deletedTrip = await TripService.deleteTrip(tripId);
      setRefreshFlag(!refreshFlag); 
      return deletedTrip;
    } catch (error) {
      setError('Error deleting trip');
    } finally {
      setLoading(false);
    }
  };
  //refreshinnnnnng
  useEffect(() => {
    getAllTrips();
  }, [refreshFlag]); 

  return {
    trips,
    loading,
    error,
    getTripById,
    getTripWithItineraries,
    createTrip,
    updateTrip,
    deleteTrip,
    refreshFlag,
    setRefreshFlag
  };
};
