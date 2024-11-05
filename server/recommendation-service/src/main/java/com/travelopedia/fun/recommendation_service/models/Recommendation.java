package com.travelopedia.fun.recommendation_service.models;

public class Recommendation {
    private String placeName;
    private String description;
    private String imageUrl;

    // Constructor
    public Recommendation(String placeName, String description, String imageUrl) {
        this.placeName = placeName;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
