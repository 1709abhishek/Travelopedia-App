import React, { useState } from "react";
import { DialogContent, DialogHeader, DialogTitle,DialogFooter} from "@/components/ui/dialog";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { useItinerary } from "../hooks/useItinerary";
import PropTypes from "prop-types";

function AddActivity({ isOpen, onClose, tripId, itineraryId, onRefresh }) {
  const { updateItinerary } = useItinerary();
  const [activities, setActivities] = useState([]);
  const [currentActivity, setCurrentActivity] = useState({
    hour: "",
    minute: "",
    ampm: "AM",
    place: "",
  });

  const resetForm = () => {
    setCurrentActivity({ hour: "", minute: "", ampm: "AM", place: "" });
    setActivities([]);
  };
  
  const handleUpdateItinerary = async (updatedItinerary) => {
    try {
        console.log("itineraryId",itineraryId);
        console.log("tripId",tripId);
        const editedItinerary = await updateItinerary(
            itineraryId,
            tripId,
            updatedItinerary
        );
      if (!editedItinerary) {
        console.error("Failed to save updated itinerary");
        return false;
      }
      setItineraries((prev) =>
        prev.map((itinerary) => itinerary.itineraryId === editedItinerary.itineraryId? editedItinerary : itinerary)
      );
      onClose();
      return true;
    } catch (error) {
      console.error("Error while saving updated itinerary:", error);
      return false;
    }
  };

  const handleActivityInputChange = (event) => {
    const { name, value } = event.target;
    setCurrentActivity((prevActivity) => ({
      ...prevActivity,
      [name]: value,
    }));
  };

  const handleAddActivity = () => {
    const formattedTime = `${(currentActivity.hour || "00").padStart(2,"0")}:${(currentActivity.minute || "00").padStart(2, "0")} ${currentActivity.ampm}`;
    if (!currentActivity.place) {
      console.error("Activity place cannot be empty");
      return;
    }
    const newActivity = {
      time: formattedTime,
      place: currentActivity.place,
    };
    setActivities((prevActivities) => [...prevActivities, newActivity]);
    setCurrentActivity({ hour: "", minute: "", ampm: "AM", place: "" });
  };

  const handleSaveAllActivities = async () => {
    const updatedItinerary = {
      tripId,
      itineraryId,
      activities,
    };

    const success = await handleUpdateItinerary(updatedItinerary);
    if (success) {
      setActivities([]); 
      onClose();
    }
    resetForm();
    onClose();
    onRefresh();
  };

  return (
    <DialogContent className="bg-gray-900 text-white">
      <DialogHeader>
        <DialogTitle>Add New Activities</DialogTitle>
      </DialogHeader>

      <div className="space-y-4">
        {activities.length > 0 && (
          <ul className="space-y-2">
            {activities.map((activity, index) => (
              <li
                key={index}
                className="flex justify-between bg-gray-800 p-2 rounded"
              >
                <span>
                  {activity.time} - {activity.place}
                </span>
              </li>
            ))}
          </ul>
        )}
        <div className="flex space-x-2">
          <Input
            type="number"
            name="hour"
            placeholder="HH"
            value={currentActivity.hour}
            onChange={handleActivityInputChange}
            max="12"
            className="bg-gray-800 text-white"
          />
          <Input
            type="number"
            name="minute"
            placeholder="MM"
            value={currentActivity.minute}
            onChange={handleActivityInputChange}
            max="59"
            className="bg-gray-800 text-white"
          />
          <select
            name="ampm"
            value={currentActivity.ampm}
            onChange={handleActivityInputChange}
            className="bg-gray-800 text-white p-2"
          >
            <option value="AM">AM</option>
            <option value="PM">PM</option>
          </select>
        </div>

        <Input
          type="text"
          name="place"
          placeholder="Enter place"
          value={currentActivity.place}
          onChange={handleActivityInputChange}
          className="bg-gray-800 text-white mt-2"
        />
        <Button
          variant="secondary"
          onClick={handleAddActivity}
          className="mt-4"
        >
          Add Activity
        </Button>
      </div>

      <DialogFooter>
        <Button onClick={onClose}>Cancel</Button>
        <Button onClick={handleSaveAllActivities} disabled={activities.length === 0}>
          Save Activities
        </Button>
      </DialogFooter>
    </DialogContent>
  );
}


AddActivity.propTypes = {
  isOpen: PropTypes.bool.isRequired,
  onClose: PropTypes.func.isRequired,
  tripId: PropTypes.string.isRequired,
  itineraryId: PropTypes.string.isRequired,
};

export default AddActivity;
