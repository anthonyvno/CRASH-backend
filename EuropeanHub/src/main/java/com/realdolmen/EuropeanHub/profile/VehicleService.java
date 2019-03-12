/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.EuropeanHub.profile;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    
    @Autowired
    private VehicleRepository vehicleRepository;
    
    public Optional<Vehicle> findVehicleById(int id){ 
        return vehicleRepository.findById(id);
    }
    
    public List<Vehicle> findAll(){
        return vehicleRepository.findAll();
    }
    
    public void deleteById(int id){
        vehicleRepository.deleteById(id);
    }
    
    public Vehicle save(Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }
    
}
