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
public class ProfileEUService {
    
    @Autowired
    private ProfileEURepository profileRepository;
    
    public Optional<ProfileEU> findProfileById(int id){ 
        return profileRepository.findById(id);
    }
    
    public List<ProfileEU> findAll(){
        return profileRepository.findAll();
    }
    
    public void deleteById(int id){
        profileRepository.deleteById(id);
    }
    
    public ProfileEU save(ProfileEU profile){
        return profileRepository.save(profile);
    }
    
}
