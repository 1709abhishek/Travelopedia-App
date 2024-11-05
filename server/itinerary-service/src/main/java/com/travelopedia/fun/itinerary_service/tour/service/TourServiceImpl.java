package com.travelopedia.fun.itinerary_service.tour.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelopedia.fun.itinerary_service.dto.ResponseWrapper;
import com.travelopedia.fun.itinerary_service.tour.beans.Tour;
import com.travelopedia.fun.itinerary_service.tour.repository.TourRepository;
@Service
public class TourServiceImpl implements TourService{
	
	@Autowired
	private TourRepository tourRepository;

	@Override
	public List<Tour> getAllTours() {
		// TODO Auto-generated method stub
		try {
			return (List<Tour>) tourRepository.findAll();
		}catch(Exception e) {
			System.err.println("Error retrieving tourdata:" + e.getMessage());
			throw new RuntimeException("Failed to retieve tour data", e);
		}
	}

	@Override
	public Optional<Tour> fetchTour(int tourId) {
		// TODO Auto-generated method stub
		try {
			Optional<Tour> tourRecord = tourRepository.findById(tourId);
			if(tourRecord.isPresent()) {
				return tourRecord;
			}else {
				throw new Exception("tour record not found");
			}
		}catch(Exception e) {
			System.err.println("Error retrieving record by tourId:" + e.getMessage());
			return Optional.empty();
		}
	}

	@Override
	public ResponseWrapper<Tour> saveTourData(Tour tour) {
		// TODO Auto-generated method stub
		try {
			Optional<Tour> tourRecord = tourRepository.findById(tour.getTourId());
			if(tourRecord.isPresent()) {
				return new ResponseWrapper<>("Record already exists");
			}else {
				//validateData(tour);
				Tour savedTour = tourRepository.save(tour);
				return new ResponseWrapper<>("Record created successfully",savedTour);
			}
		}catch(Exception e) {
			System.err.println("Error saving tourdata:" + e.getMessage());
			throw new RuntimeException("Failed to save tour data", e);
		}
	}

	@Override
	public ResponseWrapper<Tour> updateTourData(Tour tour) {
		// TODO Auto-generated method stub
		try {
			Optional<Tour> tourRecord = tourRepository.findById(tour.getTourId());
			if(tourRecord.isPresent()) {
				//validateData(tour);
				Tour entityToUpdate = tourRecord.get();
				entityToUpdate.setName(tour.getName());
				entityToUpdate.setPlaces(tour.getPlaces());
				entityToUpdate.setPrice(tour.getPrice());
				//Add fields to updated
				Tour updatedTour = tourRepository.save(entityToUpdate);
				return new ResponseWrapper<>("Record updated successfully",updatedTour);
			}else {
				return new ResponseWrapper<>("Tour Record doen't exxist");
			}
		}catch(Exception e) {
			System.err.println("Error updating tourdata:" + e.getMessage());
			throw new RuntimeException("Failed to update tour data", e);
		}
	}

	@Override
	public ResponseWrapper<Tour> deleteTourData(int tourId) {
		// TODO Auto-generated method stub
		try {

			Optional<Tour> existingTourRecord = tourRepository.findById(tourId);
			
			if(existingTourRecord.isPresent()) {
				tourRepository.deleteById(tourId);
				return new ResponseWrapper<>("Record deleted successfully");
			}else {
				return new ResponseWrapper<>("Tour record doesnot exist");
			}
		}catch(Exception e) {
			System.err.println("Unexpected error while deleting tour record:" + e.getMessage());
			throw new RuntimeException("Failed to delete tour data", e);
		}
		
	}

}
