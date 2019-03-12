/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.EuropeanHub.insurer;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InsurerService {
    
    @Autowired
    private InsurerRepository insurerRepository;
    
    public Optional<Insurer> findInsurerById(int id){
        return insurerRepository.findById(id);
    }
    
    public List<Insurer> findAll(){
        return insurerRepository.findAll();
    }
    
    public void deleteById(int id){
        insurerRepository.deleteById(id);
    }
    
    public Insurer save(Insurer insurer){
        return insurerRepository.save(insurer);
    }
    
}
