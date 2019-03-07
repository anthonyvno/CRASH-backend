/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.EuropeanHub.profile;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProfileEUController {
    
    private final ProfileEURepository profileRepository ;

	ProfileEUController(ProfileEURepository profileRepository) {
		this.profileRepository = profileRepository;
	}
    

        @GetMapping("/profiles")
	List<ProfileEU> all() {
		return profileRepository.findAll();
	}

	@PostMapping("/profiles")
	ProfileEU newProfile(@RequestBody ProfileEU newProfile) {
		return profileRepository.save(newProfile);
	}

	// Single item

	@GetMapping("/profiles/{id}")
	ProfileEU one(@PathVariable int id) {

		return profileRepository.findById(id)
			.orElseThrow(() -> new ProfileEUNotFoundException(id));
	}

	@PutMapping("/profiles/{id}")
	ProfileEU replaceProfile(@RequestBody ProfileEU newProfile, @PathVariable int id) {

		return profileRepository.findById(id)
			.map(profile -> {
				profile.setFirstName(newProfile.getFirstName());
				profile.setLastName(newProfile.getLastName());
                                profile.setEmail(newProfile.getEmail());
                                profile.setLicense(newProfile.getLicense());
                                profile.setVehicles(newProfile.getVehicles());
				return profileRepository.save(profile);
			})
			.orElseGet(() -> {
				newProfile.setId(id);
				return profileRepository.save(newProfile);
			});
	}
        

	@DeleteMapping("/profiles/{id}")
	void deleteProfile(@PathVariable int id) {
		profileRepository.deleteById(id);
	}
    
}
