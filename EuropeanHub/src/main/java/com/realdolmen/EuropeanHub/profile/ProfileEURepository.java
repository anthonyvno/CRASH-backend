
package com.realdolmen.EuropeanHub.profile;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProfileEURepository  extends JpaRepository<ProfileEU, Integer>{
    
       // List<Profile> findByCountry(@Param("country") String country);
}
