/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.EuropeanHub.profile;

import com.realdolmen.EuropeanHub.profile.Insurance;
import com.realdolmen.EuropeanHub.profile.InsuranceRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuranceService {
    @Autowired
    private InsuranceRepository insuranceRepository;
    
    public Optional<Insurance> findInsuranceById(int id){ 
        return insuranceRepository.findById(id);
    }
    
    public List<Insurance> findAll(){
        return insuranceRepository.findAll();
    }
    
    public void deleteById(int id){
        insuranceRepository.deleteById(id);
    }
    
    public Insurance save(Insurance insurance){
        return insuranceRepository.save(insurance);
    }
    
}
