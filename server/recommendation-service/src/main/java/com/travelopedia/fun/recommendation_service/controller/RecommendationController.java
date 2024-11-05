package com.travelopedia.fun.recommendation_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelopedia.fun.recommendation_service.models.Recommendation;
import com.travelopedia.fun.recommendation_service.models.UserPreferences;
import com.travelopedia.fun.recommendation_service.service.RecommendationService;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/last-itineraries/{userId}")
    public ResponseEntity<List<Recommendation>> getRecommendationsByLastItineraries(@PathVariable String userId) {
        List<Recommendation> recommendations = recommendationService.getRecommendationsFromItineraries(userId);
        return ResponseEntity.ok(recommendations);
    }

    @PostMapping("/preferences/{userId}")
    public ResponseEntity<Void> setUserPreferences(@PathVariable String userId, @RequestBody UserPreferences preferences) {
        recommendationService.setUserPreferences(userId, preferences);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/preferences/{userId}")
    public ResponseEntity<UserPreferences> getUserPreferences(@PathVariable String userId) {
        UserPreferences preferences = recommendationService.getUserPreferences(userId);
        return ResponseEntity.ok(preferences);
    }

    @DeleteMapping("/preferences/{userId}")
    public ResponseEntity<Void> deleteUserPreferences(@PathVariable String userId) {
        recommendationService.deleteUserPreferences(userId);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/preferences")
    public ResponseEntity<List<UserPreferences>> getAllUserPreferences() {
        List<UserPreferences> preferences = recommendationService.getAllUserPreferences();
        return ResponseEntity.ok(preferences);
    }
}
