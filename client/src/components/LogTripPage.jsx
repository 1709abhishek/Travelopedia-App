import React, { useState } from "react";
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from "@/components/ui/card";
import { PlusCircle, MapPin, Edit, Trash, Clock, Calendar } from "lucide-react";
import { Button } from "@/components/ui/button";
import Header from "./Header.jsx";
import { format } from "date-fns";
import CreateTripModal from "./CreateTripModal.jsx";
import EditTripModal from "./EditTripModal.jsx";
import { useTrip } from "../hooks/useTrip";
import { useNavigate } from "react-router-dom";

function LogTripPage() {

  const navigate = useNavigate();
  const handleViewItineraries = (tripId,destination) => {
    console.log("Trip ID passssssssssing:", tripId);
    if (!tripId) {
      console.error("Trip ID is undefined or invalid:", tripId);
      return;
    }
    navigate(`/itineraries/${tripId}`, { state: { destination } }); 
  };
  const { trips, loading, error, createTrip, updateTrip, deleteTrip } = useTrip();
  const [newTrip, setNewTrip] = useState({
    tripId: "",
    destination: "",
    country: "",
    duration: "",
    date: format(new Date(), "yyyy-MM-dd"),
    startDate: "",
    endDate: "",
    description: "",
    itineraries: []
  });

  const [isCreateModalOpen, setIsCreateModalOpen] = useState(false);
  const [isEditModalOpen, setIsEditModalOpen] = useState(false);

  const handleOpenCreateModal = () => {
    setNewTrip({
      tripId: "",
      destination: "",
      country: "",
      duration: "",
      date: "",
      startDate: "",
      endDate: "",
      description: "",
      itineraries: [],
    });
    setIsCreateModalOpen(true);
  };

  const handleTripInputChange = (e) => {
    const { name, value } = e.target;
    setNewTrip({ ...newTrip, [name]: value });
  };

  const handleDateChange = (name, date) => {
    const formattedDate = date ? format(date, "yyyy-MM-dd") : null;
    const updatedTrip = { ...newTrip, [name]: formattedDate };
    setNewTrip(updatedTrip);
    console.log("Updated Trip:", updatedTrip);
  };

  const handleCreateTrip = async () => {
    if (!newTrip.destination || !newTrip.country || !newTrip.startDate || !newTrip.endDate) {
      alert("Enter all fields");
      return;
    }
    try {
      await createTrip(newTrip);
      setIsCreateModalOpen(false); 
    } catch (error) {
      console.error('Error creating trip:', error);
    }
  };

  const handleEditTrip = (trip) => {
    setNewTrip(trip);
    setIsEditModalOpen(true);
  };

  const handleSaveEditTrip = async () => {
    if (!newTrip.destination || !newTrip.country || !newTrip.startDate || !newTrip.endDate) {
      alert("Enter all fields");
      return;
    }
    console.log('Updating trip with data:', newTrip);
    console.log('Updating trip with data: alisga', newTrip.tripId);
    try {
      await updateTrip(newTrip.tripId, newTrip);
      setIsEditModalOpen(false);
    } catch (error) {
      console.error('Error updating trip:', error);
    }
  };

  const handleDeleteTrip = async (id) => {
    try {
      await deleteTrip(id);
    } catch (error) {
      console.error('Error deleting trip:', error);
    }
  };

  return (
    <div className="min-h-screen bg-black text-white">
      <header className="bg-gray-900 py-8 flex justify-between items-center">
        <Header />
      </header>

      <main className="container mx-auto p-6">
        {/* Trip List */}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {trips.map((trip) => (
            <Card key={trip.id} className="bg-gray-800 border-gray-700 text-white">
              <CardHeader className="space-y-2 relative">
                <CardTitle className="flex items-center text-gray-100">
                  <MapPin className="mr-2 text-blue-600" />
                  {trip.destination}
                </CardTitle>
                <div className="absolute top-3 right-0 flex space-x-2">
                  <Button variant="link" onClick={() => handleEditTrip(trip)}>
                    <Edit className="w-4 h-4 text-blue-600" />
                  </Button>
                  <Button variant="destructive" onClick={() => handleDeleteTrip(trip.tripId)}>
                    <Trash className="w-4 h-4" />
                  </Button>
                </div>
                <CardDescription className="text-gray-300 mt-4">{trip.country}</CardDescription>
              </CardHeader>
              <CardContent>
                <p className="text-sm text-gray-300 mb-4">{trip.description}</p>
                <div className="flex items-center text-sm text-gray-400 mb-2">
                  <Clock className="mr-2 text-blue-600" /> {trip.duration}
                </div>
                <div className="flex items-center text-sm text-gray-400">
                  <Calendar className="mr-2 text-blue-600" /> {trip.date}
                </div>
              </CardContent>
              <CardFooter>
                <Button variant="secondary" onClick={() => handleViewItineraries(trip.tripId)}>
                  {/* {trip.itineraries.length > 0 ? "View Itineraries" : "Add Itineraries"} */}
                  View Itineraries
                </Button>
              </CardFooter>
            </Card>
          ))}
        </div>

        {/* Add Trip */}
        <Card className="bg-gray-800 border-gray-700 text-white flex flex-col justify-center max-w-sm items-center p-6 mt-8">
            <PlusCircle className="h-12 w-12 text-blue-600 mb-4" />
            <Button variant="outline" onClick={handleOpenCreateModal} className="border-blue-600 text-blue-600 hover:bg-blue-600 hover:text-white">Add New Trip</Button>
          </Card>
      </main>

      {/* Modals */}
      <CreateTripModal
        isOpen={isCreateModalOpen}
        onClose={() => setIsCreateModalOpen(false)}
        tripData={newTrip}
        onChange={handleTripInputChange}
        onDateChange={handleDateChange}
        onSave={handleCreateTrip}
      />

      <EditTripModal
        isOpen={isEditModalOpen}
        onClose={() => setIsEditModalOpen(false)}
        tripData={newTrip}
        onChange={handleTripInputChange}
        onDateChange={handleDateChange}
        onSave={handleSaveEditTrip}
      />
    </div>
  );
}

export default LogTripPage;
