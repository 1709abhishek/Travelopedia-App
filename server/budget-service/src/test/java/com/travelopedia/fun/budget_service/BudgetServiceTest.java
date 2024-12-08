package com.travelopedia.fun.budget_service;

import com.travelopedia.fun.budget_service.entity.Budgets;
import com.travelopedia.fun.budget_service.entity.CustomBudget;
import com.travelopedia.fun.budget_service.entity.Flights;
import com.travelopedia.fun.budget_service.entity.Hotel;
import com.travelopedia.fun.budget_service.repository.BudgetRepository;
import com.travelopedia.fun.budget_service.repository.CustomBudgetRepository;
import com.travelopedia.fun.budget_service.repository.FlightsRepository;
import com.travelopedia.fun.budget_service.repository.HotelRepository;
import com.travelopedia.fun.budget_service.service.BudgetService;
import com.travelopedia.fun.budget_service.beans.HotelBudgetRequest;
import com.travelopedia.fun.budget_service.beans.FlightBudgetRequest;
import com.travelopedia.fun.budget_service.beans.CustomBudgetRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for {@link BudgetService}. This class contains tests to ensure the proper behavior of the
 * {@link BudgetService} methods for managing budgets related to hotels, flights, and custom budget items.
 * The tests mock the behavior of the repositories and verify that the service methods correctly interact with
 * the repository layer to persist and retrieve budget data.
 */
@ExtendWith(MockitoExtension.class)
public class BudgetServiceTest {

    /**
     * Mocked repository for {@link Budgets} entities.
     */
    @Mock
    private BudgetRepository budgetRepository;

    /**
     * Mocked repository for {@link Hotel} entities.
     */
    @Mock
    private HotelRepository hotelRepository;

    /**
     * Mocked repository for {@link Flights} entities.
     */
    @Mock
    private FlightsRepository flightsRepository;

    /**
     * Mocked repository for {@link CustomBudget} entities.
     */
    @Mock
    private CustomBudgetRepository customBudgetRepository;

    /**
     * The {@link BudgetService} instance being tested.
     */
    @InjectMocks
    private BudgetService budgetService;

    /**
     * A sample {@link Budgets} object for testing purposes.
     */
    private Budgets budget;

    /**
     * A sample {@link Hotel} object for testing purposes.
     */
    private Hotel hotel;

    /**
     * A sample {@link Flights} object for testing purposes.
     */
    private Flights flight;

    /**
     * A sample {@link CustomBudget} object for testing purposes.
     */
    private CustomBudget customBudget;

    /**
     * Initializes the test data before each test case.
     * Creates sample budget, hotel, flight, and custom budget objects used in various test cases.
     */
    @BeforeEach
    void setup() {
        budget = new Budgets();
        budget.setBudgetID(1L);
        budget.setItineraryID(123);
        budget.setType("hotel");
        budget.setPrice(1000.0);

        hotel = new Hotel();
        hotel.setId(1L);
        hotel.setName("Holiday Inn");
        hotel.setPrice(500.0);
        hotel.setCity("Chicago");
        hotel.setGuests(2);
        hotel.setStartDate("2024-12-01");
        hotel.setEndDate("2024-11-07");

        flight = new Flights();
        flight.setId(1L);
        flight.setFlightNumber("TK5");
        flight.setName("Turkish Airlines");
        flight.setFromLocation("Istanbul");
        flight.setToLocation("Chicago");
        flight.setPrice(500.0);
        flight.setAdults(2);
        flight.setDepartureDate("2024-12-01");
        flight.setArrivalDate("2024-11-07");

        customBudget = new CustomBudget();
        customBudget.setId(1L);
        customBudget.setName("Exploring");
        customBudget.setPrice(300.0);
    }

    /**
     * Tests the {@link BudgetService#createHotelBudget(HotelBudgetRequest)} method.
     * Verifies that a hotel budget is created and that the corresponding {@link Budgets} and {@link Hotel} entities
     * are saved to their respective repositories.
     */
    @Test
    void testCreateHotelBudget() {
        HotelBudgetRequest request = new HotelBudgetRequest();
        request.setItineraryID(123);
        request.setType("hotel");
        request.setPrice(1000.0);
        request.setItems(Arrays.asList(new HotelBudgetRequest.Item()));

        when(budgetRepository.save(any(Budgets.class))).thenReturn(budget);
        budgetService.createHotelBudget(request);

        verify(budgetRepository, times(1)).save(any(Budgets.class));
        verify(hotelRepository, times(1)).save(any(Hotel.class));
    }

    /**
     * Tests the {@link BudgetService#createFlightBudget(FlightBudgetRequest)} method.
     * Verifies that a flight budget is created and that the corresponding {@link Budgets} and {@link Flights} entities
     * are saved to their respective repositories.
     */
    @Test
    void testCreateFlightBudget() {
        FlightBudgetRequest request = new FlightBudgetRequest();
        request.setItineraryID(123);
        request.setType("flight");
        request.setPrice(1000.0);
        request.setItems(Arrays.asList(new FlightBudgetRequest.Item()));

        when(budgetRepository.save(any(Budgets.class))).thenReturn(budget);
        budgetService.createFlightBudget(request);

        verify(budgetRepository, times(1)).save(any(Budgets.class));
        verify(flightsRepository, times(1)).save(any(Flights.class));
    }

    /**
     * Tests the {@link BudgetService#createCustomBudget(CustomBudgetRequest)} method.
     * Verifies that a custom budget is created and that the corresponding {@link Budgets} and {@link CustomBudget} entities
     * are saved to their respective repositories.
     */
    @Test
    void testCreateCustomBudget() {
        CustomBudgetRequest request = new CustomBudgetRequest();
        request.setItineraryID(123);
        request.setType("custom");
        request.setPrice(300.0);
        request.setName("Exploring");

        when(budgetRepository.save(any(Budgets.class))).thenReturn(budget);
        budgetService.createCustomBudget(request);

        verify(budgetRepository, times(1)).save(any(Budgets.class));
        verify(customBudgetRepository, times(1)).save(any(CustomBudget.class));
    }

    /**
     * Tests the {@link BudgetService#getBudgetsByItineraryID(long)} method.
     * Verifies that the method returns a list of budgets for a given itinerary ID, including related hotel entities.
     */
    @Test
    void testGetBudgetsByItineraryID() {
        when(budgetRepository.findByItineraryID(123)).thenReturn(Arrays.asList(budget));
        when(hotelRepository.findByBudget_BudgetID(1L)).thenReturn(Collections.singletonList(hotel));

        var result = budgetService.getBudgetsByItineraryID(123);
        assertNotNull(result);
        assertFalse(result.isEmpty());

        verify(budgetRepository, times(1)).findByItineraryID(123);
    }

    /**
     * Tests the {@link BudgetService#deleteBudgetsByItineraryID(long)} method.
     * Verifies that budgets and related hotel entities are deleted when requested by itinerary ID.
     */
    @Test
    void testDeleteBudgetsByItineraryID() {
        when(budgetRepository.findByItineraryID(123)).thenReturn(Arrays.asList(budget));
        when(hotelRepository.findByBudget_BudgetID(1L)).thenReturn(Collections.singletonList(hotel));

        budgetService.deleteBudgetsByItineraryID(123);

        verify(hotelRepository, times(1)).deleteAll(anyList());
        verify(budgetRepository, times(1)).delete(any(Budgets.class));
    }

    /**
     * Tests the {@link BudgetService#deleteBudgetByTypeAndId(long, String)} method.
     * Verifies that a budget and related entities are deleted when requested by budget ID and type.
     */
    @Test
    void testDeleteBudgetByTypeAndId() {
        when(budgetRepository.findById(1L)).thenReturn(Optional.of(budget));
        when(hotelRepository.findByBudget_BudgetID(1L)).thenReturn(Collections.singletonList(hotel));

        budgetService.deleteBudgetByTypeAndId(1L, "hotel");

        verify(hotelRepository, times(1)).deleteAll(anyList());
        verify(budgetRepository, times(1)).delete(any(Budgets.class));
    }

    /**
     * Tests the {@link BudgetService#deleteBudgetByTypeAndId(long, String)} method with an invalid budget type.
     * Verifies that an exception is thrown when the type provided is not a valid budget type.
     */
    @Test
    void testDeleteBudgetByInvalidType() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            budgetService.deleteBudgetByTypeAndId(1L, "invalidType");
        });

        assertEquals("Invalid budget type: invalidType", exception.getMessage());
    }
}
