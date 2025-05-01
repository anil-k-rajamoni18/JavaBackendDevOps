import axios from 'axios';

const API_BASE_URL = import.meta.env.VITE_API_URL || 'http://localhost:8081';

// ------------------ CAR APIs ------------------

export const getAllCars = async () => {
  const response = await axios.get(`${API_BASE_URL}/cars`);
  return response.data;
};

export const addCar = async (carData) => {
  const response = await axios.post(`${API_BASE_URL}/cars`, carData);
  return response.data;
};

// ------------------ RENTAL APIs ------------------

export const rentCar = async (carId, rentalData) => {
  const response = await axios.post(`${API_BASE_URL}/rentals/car/${carId}`, rentalData);
  return response.data;
};

export const getRentalById = async (rentalId) => {
  const response = await axios.get(`${API_BASE_URL}/rentals/${rentalId}`);
  return response.data;
};
