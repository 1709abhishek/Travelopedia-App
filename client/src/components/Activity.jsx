import React from "react";
import { Button } from "@/components/ui/button";
import { Edit, Trash } from "lucide-react";

function Activity({ activity, onEdit, onDelete }) {
  return (
    <li className="flex justify-between items-center mb-2">
      <span className="text-sm text-gray-400">
        {activity.hour} :{activity.minute} {activity.ampm} - {activity.place}
      </span>
      <div className="flex space-x-2">
        <Button variant="link" onClick={onEdit}>
          <Edit className="w-4 h-4 text-blue-600" />
        </Button>
        <Button variant="destructive" onClick={onDelete}>
          <Trash className="w-4 h-4" />
        </Button>
      </div>
    </li>
  );
}

export default Activity;
