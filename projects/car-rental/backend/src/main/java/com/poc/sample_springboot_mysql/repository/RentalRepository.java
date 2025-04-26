package com.poc.sample_springboot_mysql.repository;

import com.poc.sample_springboot_mysql.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {}
