import { useState } from 'react';
import ActivityService from '../services/ActivityService';

export const useActivity = () => {
  const [refreshFlag, setRefreshFlag] = useState(false);
  
  // Get activities by itinerary ID
  const getActivitiesByItinerary = async (itineraryId) => {
    try {
      const data = await ActivityService.getActivitiesByItineraryId(itineraryId);
      return data;
    } catch (error) {
      console.error('Error fetching activities by itinerary ID', error);
      return null;
    }
  };

  // Get activity by ID
  const getActivityById = async (activityId) => {
    try {
      const data = await ActivityService.getActivityById(activityId);
      return data;
    } catch (error) {
      console.error('Error fetching activity by ID', error);
      return null;
    }
  };

  // Create a new activity
  const createActivity = async (itineraryId, activity) => {
    try {
      const data = await ActivityService.createActivity(itineraryId, activity);
      return data;
    } catch (error) {
      console.error('Error creating activity', error);
      return null;
    }
  };

  // Update an activity
  const updateActivity = async (tripId, itineraryId, activityId,activity) => {
    try {
      const data = await ActivityService.updateActivity(tripId, itineraryId, activityId, activity);
      return data;
    } catch (error) {
      console.error('Error updating activity', error);
      return null;
    }
  };

  // Delete an activity
  const deleteActivity = async (tripId, itineraryId, activityId) => {
    try {
      const data = await ActivityService.deleteActivity(tripId, itineraryId, activityId);
      return data;
    } catch (error) {
      console.error('Error deleting activity', error);
      return null;
    }
  };

  const refreshActivities = () => {
    setRefreshFlag(!refreshFlag);
  };

  return {
    getActivitiesByItinerary,
    getActivityById,
    createActivity,
    updateActivity,
    deleteActivity,
    refreshActivities,
    refreshFlag
  };
};
