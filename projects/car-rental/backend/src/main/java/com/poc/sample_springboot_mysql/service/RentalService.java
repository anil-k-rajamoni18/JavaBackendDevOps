package com.poc.sample_springboot_mysql.service;

import com.poc.sample_springboot_mysql.entity.Car;
import com.poc.sample_springboot_mysql.entity.Rental;
import com.poc.sample_springboot_mysql.exception.CarNotAvailableException;
import com.poc.sample_springboot_mysql.exception.ResourceNotFoundException;
import com.poc.sample_springboot_mysql.repository.CarRepository;
import com.poc.sample_springboot_mysql.repository.RentalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalService {

    private static final Logger logger = LoggerFactory.getLogger(RentalService.class);

    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private CarRepository carRepository;

    public Rental rentCar(Long carId, Rental rental) {
        logger.info("Processing car rental request for carId: {}, customer: {}", carId, rental.getCustomerName());
        Car car = carRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car not found"));
        if (!car.isAvailable()) {
            logger.warn("Car {} is not available for rent", carId);
            throw new CarNotAvailableException("Car is not available for rent");
        }
        car.setAvailable(false);
        carRepository.save(car);

        rental.setTotalPrice(car.getPricePerDay() * rental.getRentalDays());
        Rental savedRental = rentalRepository.save(rental);
        logger.info("Rental created successfully: {}", savedRental);
        return savedRental;
    }

    public Rental getRentalById(Long id) {
        logger.info("Fetching rental details for id: {}", id);
        return rentalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rental not found"));
    }
}