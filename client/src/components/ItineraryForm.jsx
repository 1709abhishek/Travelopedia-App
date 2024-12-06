import React, { useState } from "react";
import { DialogContent, DialogHeader, DialogTitle, DialogFooter } from "@/components/ui/dialog";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import ActivityList from "./ActivityList";

function ItineraryForm({
  isOpen,
  onClose,
  handleSaveItinerary,
}) {
  const [newItinerary, setNewItinerary] = useState({ itineraryId: null, activities: [] });
  const [currentActivity, setCurrentActivity] = useState({
    hour: "",
    minute: "",
    ampm: "AM",
    place: "",
  });

  const handleActivityInputChange = (event) => {
    const { name, value } = event.target;
    setCurrentActivity((prevActivity) => ({
      ...prevActivity,
      [name]: value,
    }));
  };

  const handleAddActivity = () => {
    const formattedTime = `${currentActivity.hour}:${currentActivity.minute} ${currentActivity.ampm}`;
    const newActivity = {
      time: formattedTime,
      place: currentActivity.place,
    };
    setNewItinerary((prevItinerary) => ({
      ...prevItinerary,
      activities: [...prevItinerary.activities, newActivity],
    }));
    setCurrentActivity({ hour: "", minute: "", ampm: "AM", place: "" });
  };

  const handleSaveItineraryLocal = () => {
    if (!newItinerary.activities.length) {
      alert("Please add at least one activity.");
      return;
    }
    handleSaveItinerary(newItinerary);
  };

  return (
    <DialogContent className="bg-gray-900 text-white">
      <DialogHeader>
        <DialogTitle>Create New Itinerary</DialogTitle>
      </DialogHeader>

      <div className="space-y-4">
        <ActivityList activities={newItinerary.activities} />

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

        <Button variant="secondary" onClick={handleAddActivity} className="mt-4">
          Add Activity
        </Button>
      </div>

      <DialogFooter>
        <Button onClick={onClose}>Cancel</Button>
        <Button onClick={handleSaveItineraryLocal}>Save Itinerary</Button>
      </DialogFooter>
    </DialogContent>
  );
}

export default ItineraryForm;
