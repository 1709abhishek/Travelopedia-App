package com.travelopedia.fun.recommendation_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelopedia.fun.recommendation_service.models.UserPreferences;

public interface UserPreferencesRepository extends JpaRepository<UserPreferences, Long> {
    UserPreferences findByUserId(String userId);
}
