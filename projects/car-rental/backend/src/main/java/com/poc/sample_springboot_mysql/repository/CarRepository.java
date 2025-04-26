package com.poc.sample_springboot_mysql.repository;

import com.poc.sample_springboot_mysql.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {}

