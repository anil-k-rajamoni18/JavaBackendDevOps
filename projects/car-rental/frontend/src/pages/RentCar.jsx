import React, { useState } from 'react';
import { rentCar } from '../services/api';

const RentCar = () => {
  const [carId, setCarId] = useState('');
  const [renter, setRenter] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await rentCar(carId, { renter });
      alert("🚗 Car rented successfully! 🎉");
      // Reset form fields
      setCarId('');
      setRenter('');
    } catch (error) {
      alert("❌ Failed to rent the car. Please try again. 😔");
    }
  };

  return (
    <form onSubmit={handleSubmit} className="bg-white p-4 rounded shadow-md max-w-md mx-auto mt-8">
      <h2 className="text-xl font-semibold mb-4">Rent a Car</h2>
      <input
        value={carId}
        onChange={e => setCarId(e.target.value)}
        placeholder="Car ID"
        className="w-full mb-2 p-2 border rounded"
      />
      <input
        value={renter}
        onChange={e => setRenter(e.target.value)}
        placeholder="Renter Name"
        className="w-full mb-2 p-2 border rounded"
      />
      <button type="submit" className="bg-purple-600 text-white px-4 py-2 rounded">Rent Car</button>
    </form>
  );
};

export default RentCar;