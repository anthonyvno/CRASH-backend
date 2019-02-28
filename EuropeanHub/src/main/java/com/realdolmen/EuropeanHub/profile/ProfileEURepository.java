/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.EuropeanHub.profile;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProfileEURepository  extends JpaRepository<ProfileEU, Integer>{
    
       // List<Profile> findByCountry(@Param("country") String country);
}
