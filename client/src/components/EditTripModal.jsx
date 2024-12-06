import React, { useState, useEffect } from 'react';
import { Dialog, DialogActions, DialogContent, DialogTitle, TextField, Button } from '@mui/material';
import { format } from 'date-fns';

function EditTripModal({ isOpen, onClose, tripData, onChange, onDateChange, onSave }) {
  const [localTripData, setLocalTripData] = useState(tripData);

  useEffect(() => {
    setLocalTripData(tripData);
  }, [tripData]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setLocalTripData(prevData => ({
      ...prevData,
      [name]: value,
    }));
    onChange(e);
  };

  const handleDateChange = (name, date) => {
    setLocalTripData(prevData => ({
      ...prevData,
      [name]: format(date, 'yyyy-MM-dd'),
    }));
    onDateChange(name, date); 
  };

  const handleSubmit = () => {
    onSave(localTripData); 
  };

  return (
    <Dialog open={isOpen} onClose={onClose}>
      <DialogTitle>Edit Trip</DialogTitle>
      <DialogContent>
        <TextField
          label="Destination"
          name="destination"
          value={localTripData.destination}
          onChange={handleInputChange}
          fullWidth
          margin="normal"
        />
        <TextField
          label="Country"
          name="country"
          value={localTripData.country}
          onChange={handleInputChange}
          fullWidth
          margin="normal"
        />
        <TextField
          label="Description"
          name="description"
          value={localTripData.description}
          onChange={handleInputChange}
          fullWidth
          margin="normal"
        />
        <div className="date-picker-container">
          <TextField
            type="date"
            label="Start Date"
            name="startDate"
            value={localTripData.startDate}
            onChange={(e) => handleDateChange("startDate", e.target.value)}
            fullWidth
            margin="normal"
          />
          <TextField
            type="date"
            label="End Date"
            name="endDate"
            value={localTripData.endDate}
            onChange={(e) => handleDateChange("endDate", e.target.value)}
            fullWidth
            margin="normal"
          />
        </div>
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose} color="secondary">Cancel</Button>
        <Button onClick={handleSubmit} color="primary">Save</Button>
      </DialogActions>
    </Dialog>
  );
}

export default EditTripModal;
