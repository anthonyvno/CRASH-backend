
package com.realdolmen.EuropeanHub.profile;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface ProfileEURepository  extends JpaRepository<ProfileEU, Integer>{
    
       // List<Profile> findByCountry(@Param("country") String country);
    //List<ProfileEU> findByDate(@Param("date") Date date);
}
