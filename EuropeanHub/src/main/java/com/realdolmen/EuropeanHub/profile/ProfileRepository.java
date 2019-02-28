/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.EuropeanHub.profile;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface ProfileRepository  extends JpaRepository<Profile, Integer>{
    
       // List<Profile> findByCountry(@Param("country") String country);
}
