package com.travelopedia.fun.customer_service.accounts.service;

import com.travelopedia.fun.customer_service.accounts.models.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProfileService {

    @Autowired
    private RestTemplate restTemplate;

    public Profile getProfileData(int accountId) {
        double totalDistance = calculateTotalDistanceTraveled(accountId);
        int countriesVisited = calculateNumberOfCountriesVisited(accountId);
        double globalRanking = calculateGlobalTravelRanking(totalDistance);

        return new Profile(accountId, totalDistance, countriesVisited, globalRanking);
    }

    public double calculateTotalDistanceTraveled(int accountId) {
        List<Itinerary> itineraries = fetchItineraries(accountId);
        return itineraries.stream()
                .mapToDouble(Itinerary::getItineraryDuration) 
                .sum();
    }

    public int calculateNumberOfCountriesVisited(int accountId) {
        List<Itinerary> itineraries = fetchItineraries(accountId);
        Set<String> uniqueCountries = new HashSet<>();
        itineraries.forEach(itinerary -> uniqueCountries.add(itinerary.getItineraryCountry()));
        return uniqueCountries.size();
    }

    public double calculateGlobalTravelRanking(double totalDistance) {
        if (totalDistance > 10000) return 99.9; // temporarily used static data
        else if (totalDistance > 5000) return 95.0;
        else if (totalDistance > 1000) return 80.0;
        else return 50.0;
    }

    private List<Itinerary> fetchItineraries(int accountId) {
        String url = "http://itinerary-service/api/itineraries/account/" + accountId;
        Itinerary[] itineraries = restTemplate.getForObject(url, Itinerary[].class);
        return List.of(itineraries);
    }
}
