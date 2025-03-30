package com.poc.sample_springboot_mysql.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long carId;
    private String customerName;
    private int rentalDays;
    private double totalPrice;
}
