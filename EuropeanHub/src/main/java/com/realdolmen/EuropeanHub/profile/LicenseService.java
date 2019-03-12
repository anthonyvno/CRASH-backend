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
public class LicenseService {
    
    @Autowired
    private LicenseRepository licenseRepository;
    
    public Optional<License> findLicenseById(int id){ 
        return licenseRepository.findById(id);
    }
    
    public List<License> findAll(){
        return licenseRepository.findAll();
    }
    
    public void deleteById(int id){
        licenseRepository.deleteById(id);
    }
    
    public License save(License license){
        return licenseRepository.save(license);
    }
    
}
