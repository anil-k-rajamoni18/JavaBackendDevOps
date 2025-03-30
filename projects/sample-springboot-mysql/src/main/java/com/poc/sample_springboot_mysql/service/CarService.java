package com.poc.sample_springboot_mysql.service;

import com.poc.sample_springboot_mysql.entity.Car;
import com.poc.sample_springboot_mysql.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private static final Logger logger = LoggerFactory.getLogger(CarService.class);

    @Autowired
    private CarRepository carRepository;

    public Car addCar(Car car) {
        logger.info("Adding a new car: {}", car);
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        logger.info("Fetching all available cars");
        return carRepository.findAll();
    }
}