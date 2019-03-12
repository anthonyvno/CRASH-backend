package com.realdolmen.EuropeanHub.profile;

import com.realdolmen.EuropeanHub.common.NotFoundException;
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

    private final LicenseService licenseService;

    LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @GetMapping("/licenses")
    List<License> all() {
        return licenseService.findAll();
    }

    @PostMapping("/licenses")
    License newLicense(@RequestBody License newLicense) {
        return licenseService.save(newLicense);
    }

    @GetMapping("/licenses/{id}")
    License one(@PathVariable int id) {
        return licenseService.findLicenseById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @PutMapping("/licenses/{id}")
    License replaceLicense(@RequestBody License newLicense, @PathVariable int id) {

        return licenseService.findLicenseById(id)
                .map(license -> {
                    license.setCategory(newLicense.getCategory());
                    license.setExpires(newLicense.getExpires());
                    license.setLicenseNumber(newLicense.getLicenseNumber());
                    return licenseService.save(license);
                })
                .orElseGet(() -> {
                    newLicense.setId(id);
                    return licenseService.save(newLicense);
                });
    }

    @DeleteMapping("/licenses/{id}")
    void deleteLicense(@PathVariable int id) {
        licenseService.deleteById(id);
    }

}
