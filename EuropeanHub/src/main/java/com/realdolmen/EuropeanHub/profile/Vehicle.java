package com.realdolmen.EuropeanHub.profile;

import com.realdolmen.EuropeanHub.insurer.Insurance;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class Vehicle {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    private Insurance insurance;
    
    private String country;
    private String licensePlate;
    private String brand;
    private String model;
    private String type;

}
