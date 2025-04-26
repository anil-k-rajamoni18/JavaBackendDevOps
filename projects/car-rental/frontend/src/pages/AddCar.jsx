import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { addCar } from '../services/api';

const AddCar = () => {
  const [car, setCar] = useState({ brand: '', model: '', pricePerDay: '' });
  const navigate = useNavigate(); // for navigation

  const handleChange = e => {
    setCar({ ...car, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await addCar({ ...car, pricePerDay: parseFloat(car.pricePerDay) });
      alert('✅ Car added successfully!');
      setCar({ brand: '', model: '', pricePerDay: '' });
      navigate('/'); // Redirect to Home
    } catch (error) {
      console.error('Error adding car:', error);
      alert('❌ Failed to add car. Please try again.');
    }
  };

  return (
    <form onSubmit={handleSubmit} className="bg-white p-4 rounded shadow-md max-w-md mx-auto mt-6">
      <h2 className="text-xl font-semibold mb-4">Add a New Car</h2>
      <input name="brand" value={car.brand} onChange={handleChange} placeholder="Brand" className="w-full mb-2 p-2 border rounded" />
      <input name="model" value={car.model} onChange={handleChange} placeholder="Model" className="w-full mb-2 p-2 border rounded" />
      <input name="pricePerDay" value={car.pricePerDay} onChange={handleChange} placeholder="Price Per Day" type="number" className="w-full mb-2 p-2 border rounded" />
      <button type="submit" className="bg-green-600 hover:bg-green-700 text-white px-4 py-2 rounded">Add Car</button>
    </form>
  );
};

export default AddCar;
