import { Button } from "@/components/ui/button";
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from "@/components/ui/card";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogFooter } from "@/components/ui/dialog";
// import { ScrollArea } from "@/components/ui/scroll-area";
import { Calendar, Clock, MapPin, PlusCircle, Edit, Trash } from 'lucide-react';
import React, { useState } from 'react';
import Header from "../components/Header.jsx";
import { Input } from "../components/ui/input";
import Itineraries from "./Itineraries.jsx";
import { useNavigate } from "react-router-dom";
import { format } from 'date-fns';
import DatePicker from "react-datepicker"; 
import "react-datepicker/dist/react-datepicker.css"; 

function LogTripPage() {
  const navigate = useNavigate();
  //Trips state
  const [trips, setTrips] = useState([]);
  const [newTrip, setNewTrip] = useState({
    destination: '',
    country: '',
    duration: '',
    date: format(new Date(), 'yyyy-MM-dd'),
    startDate: '',
    endDate: '',
    description: '',
    itineraries: []
  });
  const [isTripModalOpen, setIsTripModalOpen] = useState(false);
  const [selectedTrip, setSelectedTrip] = useState(null);
  const [isItineraryModalOpen, setIsItineraryModalOpen] = useState(false);

  // Handler for trip input changes
  const handleTripInputChange = (e) => {
    const { name, value } = e.target;
    setNewTrip({...newTrip, [name]: value});
  };
  // Handler for date changes
  const handleDateChange = (name, date) => {
    setNewTrip({ ...newTrip, [name]: date });
  };


  // Create trip handler
  const handleCreateTrip = () => {
    if (!newTrip.destination || !newTrip.country || !newTrip.duration) {
      alert("Enter all fields");
      return;
    }

    setTrips([...trips, {
       ...newTrip, 
       id: trips.length + 1,
       date : format(new Date(), 'yyyy-MM-dd')
      }]);

    setNewTrip({
      destination: "",
      country: "",
      duration: "",
      date: format(new Date(), 'yyyy-MM-dd'),
      startDate: '',
      endDate: '',
      description: "",
      itineraries: []
    });
    setIsTripModalOpen(false);
  };

  const handleDeleteTrip = (tripId) => {
    setTrips(trips.filter((trip) => trip.id !== tripId));
    setIsTripModalOpen(false);
    setSelectedTrip(null);
  }

  const handleEditTrip = (trip) => {
    setNewTrip(trip);
    setIsTripModalOpen(true);
    setSelectedTrip(null);
  }
  // Add itinerary handler
  // const handleViewItineraries = (trip) => {
  //   setSelectedTrip(trip);
  //   setIsItineraryModalOpen(true);
  // }

  const handleViewItineraries = (trip) => {
    setSelectedTrip(trip);
    navigate("/itineraries", { state: { trip } });
  };
  

  return (
    <div className="min-h-screen bg-black text-white">

      <header className="bg-gray-900 py-8 flex justify-between items-center">
        <Header />
      </header>

      <main className="container mx-auto p-6">

        {/* displaying trip cards */}
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
                  <Button variant="destructive" onClick={() => handleDeleteTrip(trip.id)}>
                    <Trash className="w-4 h-4" />
                  </Button>
                </div>
                <CardDescription className="text-gray-300 mt-4">{trip.country}</CardDescription>
              </CardHeader>
              <CardContent>
                <p className="text-sm text-gray-300 mb-4">{trip.description}</p>
                <div className="flex items-center text-sm text-gray-400 mb-2">
                  <Clock className="mr-2 h-4 w-4 text-blue-600" />
                  {trip.duration}
                </div>
                <div className="flex items-center text-sm text-gray-400">
                  <Calendar className="mr-2 h-4 w-4 text-blue-600" />
                  {trip.date}
                </div>
              </CardContent>
              <CardFooter>
                <Button variant="secondary" onClick={() => handleViewItineraries(trip)}>
                  {trip.itineraries.length > 0 ? "View Itineraries" : "Add Itineraries"}
                </Button>
              </CardFooter>
            </Card>
          ))}
        </div>


        {/* Add Trip */}

        {/* <Button className="mt-8 bg-blue-600 hover:bg-blue-700" onClick={() => setIsTripModalOpen(true)}>
          <PlusCircle className="mr-2" />
          Add New Trip
        </Button> */}
        
        <Card className="bg-gray-800 border-gray-700 text-white flex flex-col justify-center max-w-sm items-center p-6 mt-8">
            <PlusCircle className="h-12 w-12 text-blue-600 mb-4" />
            <Button variant="outline" onClick={() => setIsTripModalOpen(true)} className="border-blue-600 text-blue-600 hover:bg-blue-600 hover:text-white">Add New Trip</Button>
          </Card>

        {/* Trip Modal creation */}
        <Dialog open={isTripModalOpen} onOpenChange={setIsTripModalOpen}>
          <DialogContent className="bg-gray-900 text-white">
            <DialogHeader>
              <DialogTitle> New Trip </DialogTitle>
            </DialogHeader>
            <Input
              placeholder="Destination"
              name="destination"
              value={newTrip.destination}
              onChange={handleTripInputChange}
            />
            <Input
              placeholder="Country"
              name="country"
              value={newTrip.country}
              onChange={handleTripInputChange}
            />
            <Input
              placeholder="Duration"
              name="duration"
              value={newTrip.duration}
              onChange={handleTripInputChange}
            />
            {/* <Input
              placeholder="Date"
              name="date"
              value={newTrip.date}
              onChange={handleTripInputChange}
            /> */}
            {/* <Input
              placeholder="Start Date"
              name="startDate"
              value={newTrip.startDate}
              onChange={handleTripInputChange}
            />
            <Input
              placeholder="End Date"
              name="endDate"
              value={newTrip.endDate}
              onChange={handleTripInputChange}
            /> */}
             {/* TODO: Fix Styling  */}
              <DatePicker
                placeholderText="Start Date"
                selected={newTrip.startDate}
                onChange={(date) => handleDateChange('startDate', date)}
                dateFormat="yyyy-MM-dd"
                className="p-1.5 bg-gray-900 border border-gray-500 rounded"
              />
          
              <DatePicker
                placeholderText="End Date"
                selected={newTrip.endDate}
                onChange={(date) => handleDateChange('endDate', date)}
                dateFormat="yyyy-MM-dd"
                className="p-1.5 bg-gray-900 border border-gray-500 rounded"
              />
            
            <Input
              placeholder="Description"
              name="description"
              value={newTrip.description}
              onChange={handleTripInputChange}
            />
            <DialogFooter>
              <Button onClick={handleCreateTrip}>Create</Button>
            </DialogFooter>
          </DialogContent>
        </Dialog>


        {/* Itineraries Component */}
        {selectedTrip && (
          <Itineraries
            isOpen={isItineraryModalOpen}
            setIsOpen={setIsItineraryModalOpen}
            selectedTrip={selectedTrip}
            setTrips={setTrips}
            trips={trips}
          />
        )}


      </main>
    </div>
  );
}

export default LogTripPage;
