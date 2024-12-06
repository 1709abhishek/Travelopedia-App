
import React from "react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
function ActivityForm({ activity, onChange, onSave, onCancel }) {
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    onChange(name, value);
  };
 
  return (
    <div>
        <div className="space-y-4">
        <div className="flex space-x-2">
          <Input
            type="number"
            name="hour"
            placeholder="HH"
            value={activity.hour}
            onChange={handleInputChange}
            className="bg-gray-800 text-white"
          />
          <Input
            type="number"
            name="minute"
            placeholder="MM"
            value={activity.minute}
            onChange={handleInputChange}
            className="bg-gray-800 text-white"
          />
          <select
            name="ampm"
            value={activity.ampm}
            onChange={handleInputChange}
            className="bg-gray-800 text-white p-2"
          >
            <option value="AM">AM</option>
            <option value="PM">PM</option>
          </select>
        </div>

        <Input
          type="text"
          name="place"
          placeholder="Place"
          value={activity.place}
          onChange={handleInputChange}
          className="bg-gray-800 text-white mt-2"
        />

        <div className="flex justify-left space-x-2 mt-2">
          <Button onClick={onSave}>
            Update Activity
          </Button>
          <Button onClick={onCancel} variant="secondary">
            Cancel
          </Button>
        </div>
      </div>
       {/* </DialogContent> */}    
    </div>
  );
}

export default ActivityForm;
