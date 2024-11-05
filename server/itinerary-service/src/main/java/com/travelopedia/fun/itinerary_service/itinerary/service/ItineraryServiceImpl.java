package com.travelopedia.fun.itinerary_service.itinerary.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.travelopedia.fun.itinerary_service.dto.ResponseWrapper;
import com.travelopedia.fun.itinerary_service.itinerary.beans.Itinerary;
import com.travelopedia.fun.itinerary_service.itinerary.repository.ItineraryRepository;

public class ItineraryServiceImpl implements ItineraryService{
	
	@Autowired
	private ItineraryRepository itineraryRepository;

	@Override
	public List<Itinerary> getAllItineraries() {
		// TODO Auto-generated method stub
		try {
			return (List<Itinerary>) itineraryRepository.findAll();
		}catch(Exception e) {
			System.err.println("Error retrieving itinerary data:" + e.getMessage());
			throw new RuntimeException("Failed to retieve itinerary data", e);
		}
	}

	@Override
	public Optional<Itinerary> fetchItinerary(int itineraryId) {
		// TODO Auto-generated method stub
		try {
			Optional<Itinerary> itineraryRecord = itineraryRepository.findById(itineraryId);
			if(itineraryRecord.isPresent()) {
				return itineraryRecord;
			}else {
				throw new Exception("Itinerary record not found");
			}
		}catch(Exception e) {
			System.err.println("Error retrieving record by itineraryId:" + e.getMessage());
			return Optional.empty();
		}
	}

	@Override
	public ResponseWrapper<Itinerary> saveItineraryData(Itinerary itinerary) {
		// TODO Auto-generated method stub
		try {
			Optional<Itinerary> itineraryRecord = itineraryRepository.findById(itinerary.getItineraryId());
			if(itineraryRecord.isPresent()) {
				return new ResponseWrapper<>("Record already exists");
			}else {
				//validateData(itinerary);
				Itinerary savedItinerary = itineraryRepository.save(itinerary);
				return new ResponseWrapper<>("Record created successfully",savedItinerary);
			}
		}catch(Exception e) {
			System.err.println("Error saving Itinerary:" + e.getMessage());
			throw new RuntimeException("Failed to save Itinerary", e);
		}
	}

	@Override
	public ResponseWrapper<Itinerary> updateItineraryData(Itinerary itinerary) {
		// TODO Auto-generated method stub
		try {
			Optional<Itinerary> itineraryRecord = itineraryRepository.findById(itinerary.getItineraryId());
			if(itineraryRecord.isPresent()) {
				//validateData(tour);
				Itinerary entityToUpdate = itineraryRecord.get();
				entityToUpdate.setItineraryCountry(itinerary.getItineraryCountry());
				entityToUpdate.setItineraryDescription(itinerary.getItineraryDescription());
				entityToUpdate.setItineraryDuration(itinerary.getItineraryDuration());
				//Add fields to updated later on
				Itinerary updatedItinerary = itineraryRepository.save(entityToUpdate);
				return new ResponseWrapper<>("Record updated successfully",updatedItinerary);
			}else {
				return new ResponseWrapper<>("Itinerary Record doen't exxist");
			}
		}catch(Exception e) {
			System.err.println("Error updating Itinerary:" + e.getMessage());
			throw new RuntimeException("Failed to update Itinerary", e);
		}
	}

	@Override
	public ResponseWrapper<Itinerary> deleteItineraryData(int itineraryId) {
		// TODO Auto-generated method stub
		try {
			Optional<Itinerary> itineraryRecord = itineraryRepository.findById(itineraryId);
			
			if(itineraryRecord.isPresent()) {
				itineraryRepository.deleteById(itineraryId);
				return new ResponseWrapper<>("Record deleted successfully");
			}else {
				return new ResponseWrapper<>("Itinerary record doesnot exist");
			}
		}catch(Exception e) {
			System.err.println("Unexpected error while deleting Itinerary record:" + e.getMessage());
			throw new RuntimeException("Failed to delete Itinerary data", e);
		}
	}

}
