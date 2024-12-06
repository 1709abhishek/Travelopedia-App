import React, { useState, useEffect } from "react";
import { useParams, useLocation } from "react-router-dom";
import { useItinerary } from "../hooks/useItinerary";  
import ItineraryList from "./ItineraryList";
import { Button } from "@/components/ui/button";
import { Dialog } from "@/components/ui/dialog";
import { Link } from "react-router-dom"; 
import ItineraryForm from "./ItineraryForm";
import EditItineraryForm from "./EditItineraryForm";
import AddActivity from "./AddActivity"; 

function ItinerariesDisplayPage() {

  const { tripId } = useParams(); 
  const { getItinerariesByTripId, createItinerary, updateItinerary, deleteItinerary } = useItinerary();
  
  const [itineraries, setItineraries] = useState([]);
  const [isItineraryModalOpen, setIsItineraryModalOpen] = useState(false);
  const [isEditItineraryModalOpen, setIsEditItineraryModalOpen] = useState(false);
  const [itineraryToEdit, setItineraryToEdit] = useState(null);
  const [newItinerary, setNewItinerary] = useState({ itineraryId: null,tripId });
  const [activities, setActivities] = useState({});
  const location = useLocation();
  const [isAddActivityModalOpen, setIsAddActivityModalOpen] = useState(false);
  const { destination } = location.state || {};
  const [selectedItineraryId, setSelectedItineraryId] = useState(null);
  const [refreshKey, setRefreshKey] = useState(0);
  const [refreshTrigger, setRefreshTrigger] = useState(false);

  const handleRefresh = () => {
    setRefreshTrigger((prev) => !prev);
  };
  //fetchung itineraries by tripId
  useEffect(() => {
    const fetchItineraries = async () => {
      if (tripId) {
        try {
          const data = await getItinerariesByTripId(tripId);
          setItineraries(data || []);
        } catch (error) {
          console.error("Failed to fetch itineraries:", error);
        }
      }
    };

    fetchItineraries();
  }, [tripId, refreshTrigger]);

  const handleDeleteItinerary = async (itineraryId) => {
    await deleteItinerary(itineraryId, tripId);
    setItineraries((prev) => prev.filter((item) => item.itineraryId !== itineraryId));
    setActivities((prev) => {
      const newActivities = { ...prev };
      delete newActivities[itineraryId];
      return newActivities;
    });
  };

  //Edited Itinerary
  const handleUpdateItinerary = async (updatedItinerary) => {
    try {
      const { itineraryId } = updatedItinerary;
      const editedItinerary = await updateItinerary(itineraryId, tripId, updatedItinerary);

      if (!editedItinerary) {
        console.error("Failed to save updated itinerary");
        return;
      }

      setItineraries((prev) =>
        prev.map((itinerary) =>
          itinerary.itineraryId === editedItinerary.itineraryId ? editedItinerary : itinerary
        )
      );

      setIsAddActivityModalOpen(false); 
    } catch (error) {
      console.error("Error while saving updated itinerary:", error);
    }
  };
  
  //Newly created Itinerary
  const handleSaveItinerary = async (createdItinerary) => {
    try {
      console.log("Created Itinerary values to be passing :", createdItinerary);
      console.log("Trip ID to be passing :", tripId);
      const savedItinerary = await createItinerary(tripId, createdItinerary);
      
      if (!savedItinerary) {
        console.error("Failed to save itinerary");
        return; 
      }
      setItineraries((prev) => [...prev, savedItinerary]);
      setNewItinerary({ itineraryId: null, activities: [], tripId });   
      setIsItineraryModalOpen(false);
    } catch (error) {
      console.error("Error while saving itinerary:", error);
    }
  };

  return (
    <div className="min-h-screen bg-black text-white">
      <header className="bg-gray-900 py-8 flex justify-between items-center">
        <h1 className="text-3xl font-bold text-white">
          Itineraries for Trip : {destination}
        </h1>
        <Link to="/log-trip" className="text-blue-600 hover:text-blue-400">
          Back to Trip
        </Link>
      </header>

      <main className="container mx-auto p-6">
        {itineraries.length === 0 ? (
          <div className="text-center text-gray-400">
            <p>No itineraries created yet. Start by adding one!</p>
          </div>
        ) : (
          <ItineraryList
            itineraries={itineraries}
            setItineraries={setItineraries}
            onDelete={handleDeleteItinerary}
            onEdit={(itinerary) => {
              setItineraryToEdit(itinerary); 
              setIsEditItineraryModalOpen(true);
            }} 
            key={refreshKey} onRefresh={handleRefresh}
          />
        )}
        <Button variant="primary" onClick={() => setIsItineraryModalOpen(true)} className="mt-4">
          Add New Itinerary
        </Button>
      </main>

      {/* Dialog for creating new itinerary */}
      <Dialog open={isItineraryModalOpen} onOpenChange={setIsItineraryModalOpen}>
        <ItineraryForm
          isOpen={isItineraryModalOpen}
          onClose={() => setIsItineraryModalOpen(false)}
          handleSaveItinerary={handleSaveItinerary}
        />
      </Dialog>

      {/* Dialog for editing an itinerary */}
      <Dialog open={isEditItineraryModalOpen} onOpenChange={setIsEditItineraryModalOpen}>
        <EditItineraryForm
          isOpen={isEditItineraryModalOpen}
          onClose={() => setIsEditItineraryModalOpen(false)}
          itineraryToEdit={itineraryToEdit}
          handleUpdateExistingItinerary={handleUpdateItinerary}
        />
      </Dialog>
    </div>
  );
}

export default ItinerariesDisplayPage;
