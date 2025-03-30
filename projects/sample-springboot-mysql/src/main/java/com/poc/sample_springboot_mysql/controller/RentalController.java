package com.poc.sample_springboot_mysql.controller;

import com.poc.sample_springboot_mysql.entity.Rental;
import com.poc.sample_springboot_mysql.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rentals")
public class RentalController {
    @Autowired
    private RentalService rentalService;

    @PostMapping("/car/{carId}")
    public ResponseEntity<?> rentCar(@PathVariable Long carId, @RequestBody Rental rental) {
        try {
            return ResponseEntity.ok(rentalService.rentCar(carId, rental));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rental> getRental(@PathVariable Long id) {
        Rental rental = rentalService.getRentalById(id);
        return rental != null ? ResponseEntity.ok(rental) : ResponseEntity.notFound().build();
    }
}