import React, { useEffect, useState } from 'react';
import { getAllCars } from '../services/api';
import CarCard from '../components/CarCard/CarCard';

const Home = () => {
  const [cars, setCars] = useState([]);

  useEffect(() => {
    getAllCars().then(setCars).catch(console.error);
  }, []);

  return (
    <div className="px-4 py-6 text-white"> {/* Added text-white here */}
      <h1 className="text-3xl font-bold mb-6 text-center">Available Cars</h1>
      
      {cars.length === 0 ? (
        <p className="text-center text-lg text-white/80">No cars available at the moment.</p>
      ) : (
        <div className="flex overflow-x-auto pb-4 gap-6 px-4">
          {cars.map(car => (
            <div key={car.id} className="flex-none w-72 mx-2">
              <CarCard car={car} />
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default Home;