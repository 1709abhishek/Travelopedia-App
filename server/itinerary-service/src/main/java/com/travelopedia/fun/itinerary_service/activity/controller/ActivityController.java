package com.travelopedia.fun.itinerary_service.activity.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travelopedia.fun.itinerary_service.activity.dto.ActivityDTO;
import com.travelopedia.fun.itinerary_service.activity.entity.Activity;
import com.travelopedia.fun.itinerary_service.activity.services.ActivityService;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

	private final ActivityService activityService;

	public ActivityController(ActivityService activityService) {
		this.activityService = activityService;
	}

	@CrossOrigin(origins = "http://localhost:5173")
	@GetMapping("/getAll")
	public ResponseEntity<List<ActivityDTO>> getAllActivities() {
		List<ActivityDTO> activities = activityService.getAllActivities();
		return new ResponseEntity<>(activities, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:5173")
	@GetMapping("/getByItinerary/{itineraryId}")
	public ResponseEntity<List<ActivityDTO>> getAllActivitiesByItineraryId(@PathVariable Long itineraryId) {
		List<ActivityDTO> activities = activityService.getAllActivitiesByItineraryId(itineraryId);
		return new ResponseEntity<>(activities, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:5173")
	@GetMapping("/{activityId}")
	public ResponseEntity<ActivityDTO> getActivityById(@PathVariable Long activityId) {
		ActivityDTO activityDTO = activityService.getActivityById(activityId);
		return new ResponseEntity<>(activityDTO, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:5173")
	@PostMapping("/save")
	public ResponseEntity<ActivityDTO> createActivity(@RequestParam Long tripId, @RequestParam Long itineraryId,
			@RequestBody Activity activity) {
		ActivityDTO createdActivity = activityService.createActivity(tripId, itineraryId, activity);
		return new ResponseEntity<>(createdActivity, HttpStatus.CREATED);
	}

	@CrossOrigin(origins = "http://localhost:5173")
	@PutMapping("/update/{activityId}")
	public ResponseEntity<ActivityDTO> updateActivity(@RequestParam Long tripId, @RequestParam Long itineraryId,
			@PathVariable Long activityId, @RequestBody Activity activity) {
		ActivityDTO updatedActivity = activityService.updateActivity(tripId, itineraryId, activityId, activity);
		return new ResponseEntity<>(updatedActivity, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:5173")
	@DeleteMapping("/delete/{activityId}")
	public ResponseEntity<String> deleteActivity(@RequestParam Long tripId, @RequestParam Long itineraryId,
			@PathVariable Long activityId) {
		String message = activityService.deleteActivity(tripId, itineraryId, activityId);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
