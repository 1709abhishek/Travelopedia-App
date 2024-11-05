package com.travelopedia.fun.recommendation_service.service;

import com.travelopedia.fun.recommendation_service.models.UserPreferences;
import com.travelopedia.fun.recommendation_service.models.Recommendation;
import com.travelopedia.fun.recommendation_service.repository.UserPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.travelopedia.fun.itinerary_service.repository.ItineraryRepository;

import java.util.List;

@Service
public class RecommendationService {

    @Autowired
    private ItineraryRepository itineraryRepository;

    @Autowired
    private UserPreferencesRepository userPreferencesRepository;

    public List<Recommendation> getRecommendationsFromItineraries(String userId) {
        List<Itinerary> itineraries = itineraryRepository.findTop5ByUserIdOrderByCreatedAtDesc(userId);
        
        // todo: add logic to generate recommendations from itineraries
        return generateRecommendationsFromItineraries(itineraries);
    }

    public void setUserPreferences(String userId, UserPreferences preferences) {
        preferences.setUserId(userId);
        userPreferencesRepository.save(preferences);
    }

    public UserPreferences getUserPreferences(String userId) {
        return userPreferencesRepository.findByUserId(userId);
    }

    public List<UserPreferences> getAllUserPreferences() {
        return userPreferencesRepository.findAll();
    }

    public void deleteUserPreferences(String userId) {
        UserPreferences preferences = userPreferencesRepository.findByUserId(userId);
        if (preferences != null) {
            userPreferencesRepository.delete(preferences);
        }
    }

    public List<Recommendation> getRecommendationsByPreferences(String userId) {
        UserPreferences preferences = userPreferencesRepository.findByUserId(userId);
        return generateRecommendationsFromPreferences(preferences);
    }

    private List<Recommendation> generateRecommendationsFromItineraries(List<Itinerary> itineraries) {
        // TODO: Add recommendation logic based on the locations from the itineraries
        return List.of();
    }

    private List<Recommendation> generateRecommendationsFromPreferences(UserPreferences preferences) {
        // TODO: Add recommendation logic based on the user preferences
        return List.of();
    }
}
