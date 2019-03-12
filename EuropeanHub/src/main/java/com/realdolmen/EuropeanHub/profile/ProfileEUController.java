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
public class ProfileEUController {

    private final ProfileEUService profileService;

    ProfileEUController(ProfileEUService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profiles")
    List<ProfileEU> all() {
        return profileService.findAll();
    }

    @PostMapping("/profiles")
    ProfileEU newProfile(@RequestBody ProfileEU newProfile) {
        return profileService.save(newProfile);
    }

    @GetMapping("/profiles/{id}")
    ProfileEU one(@PathVariable int id) {

        return profileService.findProfileById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @PutMapping("/profiles/{id}")
    ProfileEU replaceProfile(@RequestBody ProfileEU newProfile, @PathVariable int id) {

        return profileService.findProfileById(id)
                .map(profile -> {
                    profile.setFirstName(newProfile.getFirstName());
                    profile.setLastName(newProfile.getLastName());
                    profile.setEmail(newProfile.getEmail());
                    profile.setLicense(newProfile.getLicense());
                    profile.setVehicles(newProfile.getVehicles());
                    return profileService.save(profile);
                })
                .orElseGet(() -> {
                    newProfile.setId(id);
                    return profileService.save(newProfile);
                });
    }

    @DeleteMapping("/profiles/{id}")
    void deleteProfile(@PathVariable int id) {
        profileService.deleteById(id);
    }

}
