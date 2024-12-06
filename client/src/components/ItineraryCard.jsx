import React, { useState } from "react";
import { Card, CardHeader, CardTitle, CardContent, CardFooter } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { MapPin, Trash, Edit } from "lucide-react";
import { Dialog } from "@/components/ui/dialog";
import AddActivity from "./AddActivity.jsx";

function ItineraryCard({ itinerary, onDelete, onEdit, onAddActivity , onRefresh}) {
  const [isAddActivityModalOpen, setIsAddActivityModalOpen] = useState(false);

  if (!itinerary) {
    return <p>Loading itinerary..!!.</p>;
  }

  const { itineraryId, activities = [] } = itinerary;
  const handleDeleteItinerary = () => {
    if (itineraryId) {
      onDelete(itineraryId);
    }
  };

  return (
    <Card className="bg-gray-800 border-gray-700 text-white mx-auto relative" style={{ width: "400px", minHeight: "auto" }}>
      <CardHeader>
        <CardTitle className="flex items-center justify-between text-gray-100">
          <span className="flex items-center">
            <MapPin className="mr-2 text-blue-600" />
            Itinerary {itineraryId || "N/A"}
          </span>
          <div className="absolute top-2 right-2 flex space-x-2">
            <Button variant="destructive" size="icon" onClick={handleDeleteItinerary}>
              <Trash className="w-4 h-4" />
            </Button>
            <Button variant="secondary" size="icon" onClick={() => onEdit(itinerary)}>
              <Edit className="w-4 h-4" />
            </Button>
          </div>
        </CardTitle>
      </CardHeader>
      <CardContent>
        <ul>
          {activities.length > 0 ? (
            activities.map((activity, index) => (
              <li key={index}>
                {activity.time} - {activity.place}
              </li>
            ))
          ) : (
            <p>No activities added yet.</p>
          )}
        </ul>
      </CardContent>
      <CardFooter className="flex justify-center items-end w-full pb-4">
        <Button variant="primary" className="w-full" onClick={() => setIsAddActivityModalOpen(true)}>
          Add Activity
        </Button>
      </CardFooter>

      {/* AddActivity Modal */}
      <Dialog open={isAddActivityModalOpen} onOpenChange={(isOpen) => {
    console.log("Dialog open state changed to:", isOpen);
    setIsAddActivityModalOpen(isOpen);
  }}>
        <AddActivity
          isOpen={isAddActivityModalOpen}
          onClose={() => setIsAddActivityModalOpen(false)}
          tripId={itinerary.tripId}
          itineraryId={itineraryId}
          onRefresh={onRefresh}
        />
      </Dialog>
    </Card>
  );
}

export default ItineraryCard;
