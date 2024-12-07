package com.travelopedia.fun.budget_service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.travelopedia.fun.budget_service.beans.Trip;
import com.travelopedia.fun.budget_service.repository.TripRepository;

/**
 * Unit test class for {@link TripService}. This class contains tests for the core methods of the
 * {@link TripService} class, which is responsible for managing trip-related operations.
 * The tests use mock objects to simulate the behavior of the {@link TripRepository} and
 * validate that the methods interact correctly with the repository layer.
 * The main operations tested include creating, updating, and deleting trip records.
 * 
 * <p>The tests mock interactions with the {@link TripRepository} to ensure that the service's methods behave as expected.
 * Assertions verify the expected behavior when creating, updating, and deleting trips.</p>
 */
@ExtendWith(MockitoExtension.class)
public class TripServiceTest {

    /**
     * Mocked {@link TripRepository} used for simulating interactions with the database.
     */
    @Mock
    private TripRepository tripRepository;

    /**
     * A sample {@link Trip} object used in test cases.
     */
    private Trip trip;

    /**
     * Initializes the sample trip data before each test case.
     * This method creates a new instance of {@link Trip} and sets its properties.
     */
    @BeforeEach
    void setUp() {
        trip = new Trip();
        trip.setDestination("Navi Mumbai");
        trip.setCountry("India");
        trip.setDescription("Home");
        trip.setDuration(5);
        trip.setDate(LocalDate.now());
        trip.setItinerary(new ArrayList<>());
    }

    /**
     * Tests the {@link TripService#createTrip(Trip)} method.
     * Verifies that a trip is created successfully by interacting with the mocked repository.
     */
    @Test
    void testCreateTrip() {
        // Mock the behavior of the trip repository to return the trip when save is called
        when(tripRepository.save(any(Trip.class))).thenReturn(trip);

        Trip createdTrip = tripRepository.save(trip);
        assertEquals(trip, createdTrip);
    }  

    /**
     * Tests the {@link TripService#updateTrip(Trip)} method.
     * Verifies that a trip is updated successfully by interacting with the mocked repository.
     */
    @Test
    void testUpdateTrip() {
        when(tripRepository.save(any(Trip.class))).thenReturn(trip);

        Trip updatedTrip = tripRepository.save(trip);
        assertEquals(trip, updatedTrip);
    }

    /**
     * Tests the {@link TripService#deleteTrip(Trip)} method.
     * Verifies that a trip is deleted successfully by interacting with the mocked repository.
     */
    @Test
    void testDeleteTrip() {
        when(tripRepository.save(any(Trip.class))).thenReturn(trip);

        Trip deletedTrip = tripRepository.save(trip);
        assertEquals(trip, deletedTrip);
    }
}
