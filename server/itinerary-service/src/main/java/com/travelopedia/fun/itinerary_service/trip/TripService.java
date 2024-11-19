package com.travelopedia.fun.itinerary_service.trip;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TripService {

    private final TripRepository tripRepository;
    private final ModelMapper modelMapper;

    public TripService(TripRepository tripRepository, ModelMapper modelMapper) {
        this.tripRepository = tripRepository;
        this.modelMapper = modelMapper;
    }

    public List<Trip> findAllTrips() {
        return tripRepository.findAll();
    }

    public Optional<Trip> findTripById(Long tripId) {
        return tripRepository.findById(tripId);
    }

    public Trip createOrUpdateTrip(Trip trip) {
        Trip savedTrip = tripRepository.save(trip);
        return modelMapper.map(savedTrip, Trip.class);
    }

    public void deleteTrip(Long tripId) {
        tripRepository.deleteById(tripId);
    }
}
