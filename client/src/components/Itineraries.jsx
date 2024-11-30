import React, { useState } from "react";
import { Card, CardHeader, CardTitle, CardContent, CardFooter } from "@/components/ui/card";
import { MapPin, Edit, Trash } from "lucide-react";
import { Button } from "@/components/ui/button";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogFooter } from "@/components/ui/dialog";
import { Input } from "@/components/ui/input";
import { useLocation, Link } from "react-router-dom";

function Itineraries() {
  const [itineraries, setItineraries] = useState([]);
  const [newItinerary, setNewItinerary] = useState({ activities: [] });
  const [newActivity, setNewActivity] = useState({ hour: "", minute: "", ampm: "", place: "" });
  const [isItineraryModalOpen, setIsItineraryModalOpen] = useState(false);
  const [isEditModalOpen, setIsEditModalOpen] = useState(false);
  const [editItinerary, setEditItinerary] = useState(null);
  const [newEditActivity, setNewEditActivity] = useState({ hour: "", minute: "", ampm: "", place: "" });
  const [showNewActivityInputs, setShowNewActivityInputs] = useState(false);
  const [editActivityIndex, setEditActivityIndex] = useState(null);

  // updated logic to render trip data
//   TODO: fix later while integrating
  const location = useLocation(); 
  const { trip } = location.state || {}; 
  if (!trip) {
    return <p>No trip data available!</p>; 
  }

  const handleCreateItinerary = () => {
    if (newItinerary.activities.length === 0) {
      alert("Please add at least one activity");
      return;
    }
    setItineraries([...itineraries, { ...newItinerary, id: itineraries.length + 1 }]);
    setNewItinerary({ activities: [] });
    setIsItineraryModalOpen(false);
  };

  const handleEditItinerary = (itinerary) => {
    setEditItinerary(itinerary);
    setIsEditModalOpen(true);
  };

  const handleAddActivity = () => {
    if (!newActivity.hour || !newActivity.minute || !newActivity.ampm || !newActivity.place) {
      alert("Please fill in all fields!");
      return;
    }
    const time = `${newActivity.hour}:${newActivity.minute} ${newActivity.ampm}`;
    setNewItinerary({
      ...newItinerary,
      activities: [...newItinerary.activities, { time, place: newActivity.place }],
    });
    setNewActivity({ hour: "", minute: "", ampm: "", place: "" });
  };

  const handleActivityInputChange = (e) => {
    const { name, value } = e.target;
    setNewActivity({ ...newActivity, [name]: value });
  };

  const handleEditActivity = (index) => {
    setEditActivityIndex(index);
    setNewEditActivity({
      hour: editItinerary.activities[index].time.split(":")[0],
      minute: editItinerary.activities[index].time.split(":")[1].split(" ")[0],
      ampm: editItinerary.activities[index].time.split(" ")[1],
      place: editItinerary.activities[index].place,
    });
  };

  const handleEditActivityInputChange = (e) => {
    const { name, value } = e.target;
    setNewEditActivity({ ...newEditActivity, [name]: value });
  };

  const handleSaveEditActivity = () => {
    const updatedActivities = [...editItinerary.activities];
    const time = `${newEditActivity.hour}:${newEditActivity.minute} ${newEditActivity.ampm}`;
    updatedActivities[editActivityIndex] = { time, place: newEditActivity.place };
    setEditItinerary({ ...editItinerary, activities: updatedActivities });
    setNewEditActivity({ hour: "", minute: "", ampm: "", place: "" });
    setEditActivityIndex(null);
  };

  const handleSaveItinerary = () => {
    const updatedItineraries = itineraries.map((itinerary) =>
      itinerary.id === editItinerary.id ? { ...editItinerary } : itinerary
    );
    setItineraries(updatedItineraries);
    setIsEditModalOpen(false);
  };

  const handleDeleteActivity = (index) => {
    const updatedActivities = [...editItinerary.activities];
    updatedActivities.splice(index, 1);
    setEditItinerary({ ...editItinerary, activities: updatedActivities });
  };  

  const handleDeleteItinerary = (id) => {
    const updatedItineraries = itineraries.filter((itinerary) => itinerary.id !== id);
    setItineraries(updatedItineraries);
  };

  return (
    <div className="min-h-screen bg-black text-white">
      <header className="bg-gray-900 py-8 flex justify-between items-center">
        <h1 className="text-3xl font-bold text-white">Itineraries for {trip.destination}</h1>
        <Link to="/log-trip" className="text-blue-600 hover:text-blue-400">Back to Trip</Link>
      </header>

      <main className="container mx-auto p-6">
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {itineraries.map((itinerary) => (
            <Card key={itinerary.id} className="bg-gray-800 border-gray-700 text-white mx-auto relative" style={{ width: "400px", minHeight: "auto" }}>
              <CardHeader>
                <CardTitle className="flex items-center justify-between text-gray-100">
                  <span className="flex items-center">
                    <MapPin className="mr-2 text-blue-600" />
                    Itinerary {itinerary.id}
                  </span>
                  <Button variant="destructive" size="icon" className="absolute top-2 right-2" onClick={() => handleDeleteItinerary(itinerary.id)}>
                    <Trash className="w-4 h-4" />
                  </Button>
                </CardTitle>
              </CardHeader>

              <CardContent className="flex flex-col justify-between items-center">
                <ul className="text-md text-white-400 mb-4 flex-grow">
                  {itinerary.activities.length > 0 ? (
                    itinerary.activities.map((activity, index) => (
                      <li key={index} className="mb-1">
                        {activity.time} - {activity.place}
                      </li>
                    ))
                  ) : (
                    <p className="text-sm text-gray-400">No activities added yet.</p>
                  )}
                </ul>
              </CardContent>

              <CardFooter className="flex justify-center items-end w-full pb-4">
                <Button variant="primary" onClick={() => handleEditItinerary(itinerary)} className="w-full">
                  Edit Itinerary
                </Button>
              </CardFooter>
            </Card>
          ))}
        </div>

        <div className="mt-8 flex justify-center">
          <Button variant="primary" onClick={() => setIsItineraryModalOpen(true)}>
            Add New Itinerary
          </Button>
        </div>
      </main>

      <Dialog open={isItineraryModalOpen} onOpenChange={setIsItineraryModalOpen}>
        <DialogContent className="bg-gray-900 text-white">
          <DialogHeader>
            <DialogTitle>Create New Itinerary</DialogTitle>
          </DialogHeader>

          <div className="space-y-4">
            <ul>
              {newItinerary.activities.map((activity, index) => (
                <li key={index} className="text-sm text-gray-400">
                  {activity.time} - {activity.place}
                </li>
              ))}
            </ul>

            <div className="flex space-x-2">
              <Input
                type="number"
                name="hour"
                placeholder="HH"
                value={newActivity.hour}
                onChange={handleActivityInputChange}
                max="12"
              />
              <Input
                type="number"
                name="minute"
                placeholder="MM"
                value={newActivity.minute}
                onChange={handleActivityInputChange}
                max="59"
              />
              <select
                name="ampm"
                value={newActivity.ampm}
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
              value={newActivity.place}
              onChange={handleActivityInputChange}
            />
            <Button variant="secondary" onClick={handleAddActivity}>
              Add Activity
            </Button>
          </div>

          <DialogFooter>
            <Button onClick={() => setIsItineraryModalOpen(false)}>Cancel</Button>
            <Button onClick={handleCreateItinerary}>Save Itinerary</Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>

      <Dialog open={isEditModalOpen} onOpenChange={setIsEditModalOpen}>
        <DialogContent className="bg-gray-900 text-white">
          <DialogHeader>
            <DialogTitle>Edit Itinerary</DialogTitle>
          </DialogHeader>

          <ul>
            {editItinerary?.activities.map((activity, index) => (
              <li key={index} className="flex justify-between items-center">
                <span className="text-sm text-gray-400">
                  {activity.time} : {activity.place}
                </span>
                <div className="flex space-x-2">
                  <Button variant="link" onClick={() => handleEditActivity(index)}>
                    <Edit className="w-4 h-4 text-blue-600" />
                  </Button>
                  <Button variant="destructive" onClick={() => handleDeleteActivity(index)}>
                    <Trash className="w-4 h-4" />
                  </Button>
                </div>
              </li>
            ))}
          </ul>

          {editActivityIndex !== null && (
            <div className="space-y-2">
              <div className="flex space-x-2">
                <Input
                  type="number"
                  name="hour"
                  value={newEditActivity.hour}
                  onChange={handleEditActivityInputChange}
                  max="12"
                />
                <Input
                  type="number"
                  name="minute"
                  value={newEditActivity.minute}
                  onChange={handleEditActivityInputChange}
                  max="59"
                />
                <select
                  name="ampm"
                  value={newEditActivity.ampm}
                  onChange={handleEditActivityInputChange}
                  className="bg-gray-800 text-white p-2"
                >
                  <option value="AM">AM</option>
                  <option value="PM">PM</option>
                </select>
              </div>

              <Input
                type="text"
                name="place"
                value={newEditActivity.place}
                onChange={handleEditActivityInputChange}
                placeholder="Place"
              />

              <Button variant="secondary" onClick={handleSaveEditActivity}>
                Save Changes
              </Button>
            </div>
          )}

          <DialogFooter>
            <Button onClick={() => setShowNewActivityInputs(true)}>Add Activity</Button>
            <Button onClick={handleSaveItinerary}>Save Itinerary</Button>
            <Button onClick={() => setIsEditModalOpen(false)}>Cancel</Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  );
}

export default Itineraries;
