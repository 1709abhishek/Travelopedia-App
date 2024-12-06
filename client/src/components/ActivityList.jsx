import React from "react";
import { Button } from "@/components/ui/button";
import { Edit, Trash } from "lucide-react";

function ActivityList({ activities, handleEditActivity, handleDeleteActivity, showActions }) {
  return (
    <div>
      {activities.map((activity) => (
        <div key={activity.activityId} className="flex justify-between items-center">
          <span className="text-sm text-gray-400">
            {`${activity.time} - ${activity.place}`}
          </span>

          {showActions && (
            <div className="flex space-x-2">
              <Button variant="link" onClick={() => handleEditActivity(activity.activityId)}>
                <Edit className="w-4 h-4 text-blue-600" />
              </Button>
              <Button variant="destructive" onClick={() => handleDeleteActivity(activity.activityId)}>
                <Trash className="w-4 h-4" />
              </Button>
            </div>
          )}
        </div>
      ))}
    </div>
  );
}

export default ActivityList;
