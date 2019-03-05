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
public class LicenseController {
    
    private final LicenseRepository licenseRepository ;

	LicenseController(LicenseRepository licenseRepository) {
		this.licenseRepository = licenseRepository;
	}
    

        @GetMapping("/licenses")
	List<License> all() {
		return licenseRepository.findAll();
	}

	@PostMapping("/licenses")
	License newLicense(@RequestBody License newLicense) {
		return licenseRepository.save(newLicense);
	}

	// Single item

	@GetMapping("/licenses/{id}")
	License one(@PathVariable int id) {

		return licenseRepository.findById(id)
			.orElseThrow(() -> new LicenseNotFoundException(id));
	}

	@PutMapping("/licenses/{id}")
	License replaceLicense(@RequestBody License newLicense, @PathVariable int id) {

		return licenseRepository.findById(id)
			.map(license -> {
				license.setCategory(newLicense.getCategory());
				license.setExpires(newLicense.getExpires());
                                license.setLicenseNumber(newLicense.getLicenseNumber());
				return licenseRepository.save(license);
			})
			.orElseGet(() -> {
				newLicense.setId(id);
				return licenseRepository.save(newLicense);
			});
	}

	@DeleteMapping("/licenses/{id}")
	void deleteLicense(@PathVariable int id) {
		licenseRepository.deleteById(id);
	}
    
}
