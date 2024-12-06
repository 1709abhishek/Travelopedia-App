import React from "react";
import ItineraryCard from "./ItineraryCard";

function ItineraryList({ itineraries, onDelete, onEdit, onRefresh }) {
  return (
    <div className="space-y-4">
      {itineraries.map((itinerary) => (
        <ItineraryCard
          key={itinerary.itineraryId}
          itinerary={itinerary}
          onDelete={onDelete}
          onEdit={() => onEdit(itinerary)}
          onRefresh={onRefresh}
        />
      ))}
    </div>
  );
}

export default ItineraryList;
