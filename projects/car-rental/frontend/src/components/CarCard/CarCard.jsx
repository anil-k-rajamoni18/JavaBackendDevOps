import React from 'react';
import { motion } from 'framer-motion';

const CarCard = ({ car }) => {
  return (
    <motion.div
      whileHover={{ scale: 1.03 }}
      initial={{ opacity: 0, y: 20 }}
      animate={{ opacity: 1, y: 0 }}
      transition={{ type: "spring", stiffness: 150 }}
      className="bg-white rounded-lg shadow-md p-4 m-2 w-64 overflow-hidden transition-all"
    >
      <h2 className="text-lg font-bold text-gray-800 truncate">
        {car.brand} - {car.model}
      </h2>
      <p className="text-sm text-gray-500 mt-1">ID: {car.id}</p>
      <p className="text-md font-semibold text-blue-600 mt-2">
        ₹{car.pricePerDay}/day
      </p>
      <motion.div
        className={`mt-3 text-sm font-medium py-1 px-2 rounded-full inline-block ${
          car.available ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800'
        }`}
        animate={{ opacity: [0.8, 1] }}
        transition={{ repeat: Infinity, duration: 2, repeatType: "reverse" }}
      >
        {car.available ? 'Available' : 'Rented'}
      </motion.div>
    </motion.div>
  );
};

export default CarCard;