import React, { useState , useEffect} from "react";
import { DialogContent, DialogHeader, DialogTitle, DialogFooter, DialogDescription } from "@/components/ui/dialog";
import { Button } from "@/components/ui/button";
import ActivityList from "./ActivityList";
import ActivityForm from "./ActivityForm";
import { useActivity } from "@/hooks/useActivity"; 

function EditItineraryForm({isOpen,onClose,itineraryToEdit,handleUpdateExistingItinerary}) 
{
  console.log("itineraryToEdit", itineraryToEdit);
  const [activityList , setActivityList] = useState(itineraryToEdit?.activities || []);
  const [showActivityForm, setShowActivityForm] = useState(false);
  const [isEditing, setIsEditing] = useState(null);
  const [currentActivityId, setCurrentActivityId] = useState(null);
  const [newActivity, setNewActivity] = useState({ hour: "", minute: "", ampm: "AM", place: "" });
  const [existingActivityToBeUpdated, setExistingActivityToBeUpdated] = useState(null);

  const { updateActivity, deleteActivity } = useActivity();

  useEffect(() => {
    if (itineraryToEdit) {
      setActivityList(itineraryToEdit.activities || []);
    }
  }, [itineraryToEdit]);

  const resetActivityForm = () => {
    setNewActivity({ hour: "12", minute: "00", ampm: "AM", place: "" });
    setExistingActivityToBeUpdated(null);
    setShowActivityForm(false);
    setIsEditing(false);
    setCurrentActivityId(null);
  };

  const handleEditActivity = (activityId) => {
      const activity = activityList.find((activity) => activity.activityId === activityId);
      console.log("Editing activity ----->:", activity);
      setExistingActivityToBeUpdated({
        hour: activity.time.split(":")[0],
        minute: activity.time.split(":")[1].split(" ")[0],
        ampm: activity.time.split(" ")[1],
        place: activity.place,
      });
      setCurrentActivityId(activityId);
      setIsEditing(true);
      setShowActivityForm(true);
    };

  

  const handleSaveEditedActivity = async () => {
    const updatedActivity = {
      activityId: currentActivityId,
      time: `${existingActivityToBeUpdated.hour.padStart(2, "0")}:${existingActivityToBeUpdated.minute.padStart(2, "0")} ${existingActivityToBeUpdated.ampm}`,
      place: existingActivityToBeUpdated.place,
    };
    try{
        const updatedActivityResponse = await updateActivity(itineraryToEdit.tripId,itineraryToEdit.itineraryId,currentActivityId,updatedActivity);
        if(updatedActivityResponse){
          setActivityList((prev) => {
            const updatedList = prev.map((activity) =>
              activity.activityId === currentActivityId ? updatedActivity : activity
            );
            return updatedList;
          });
          resetActivityForm();
        }else {
          console.error("checking 1");
        }
      }catch (error) {
        console.error("Error updating activity", error);
    }
  };

  const handleDeleteActivity = async (activityId) => {
    try {
      console.log("Deleting activity with ID:", activityId);
      console.log("tripId and itineraryId", itineraryToEdit.tripId, itineraryToEdit.itineraryId);
      await deleteActivity(itineraryToEdit.tripId, itineraryToEdit.itineraryId, activityId);
    } catch (error) {
      console.error("Error deleting activity", error);
    }
    setActivityList((prev) => prev.filter((activity) => activity.activityId !== activityId));
  };

  const saveItineraryChanges = () => {
    const updatedItinerary = { ...itineraryToEdit, activities: activityList };
    handleUpdateExistingItinerary(updatedItinerary);
    onClose();
  };

  return (
    <DialogContent className="bg-gray-900 text-white">
      <DialogHeader>
        <DialogTitle>Edit Itinerary</DialogTitle>
      </DialogHeader>
      <div className="space-y-4">
        <ActivityList
          activities={activityList}
          handleEditActivity={handleEditActivity}
          handleDeleteActivity={handleDeleteActivity}
          showActions={true}
        />
        {showActivityForm && (
          <ActivityForm
            activity={existingActivityToBeUpdated}
            onChange={(name, value) => {setExistingActivityToBeUpdated((prev) => ({ ...prev, [name]: value }))}}
            onSave={handleSaveEditedActivity}
            onCancel={resetActivityForm}
          />
        )}
      </div>

      <DialogFooter>
        <Button onClick={onClose}>Cancel</Button>
        <Button onClick={saveItineraryChanges}>Update Itinerary</Button>
      </DialogFooter>
    </DialogContent>
  );
}

export default EditItineraryForm;
