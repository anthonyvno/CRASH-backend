package com.realdolmen.EuropeanHub.profile;

import com.realdolmen.EuropeanHub.common.NotFoundException;
import com.realdolmen.EuropeanHub.insurer.InsurerMapper;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class InsuranceController {

    private final InsuranceService insuranceService;
        private final InsuranceMapper insuranceMapper;


    InsuranceController(InsuranceService insuranceService, InsuranceMapper insuranceMapper) {
        this.insuranceService = insuranceService;
        this.insuranceMapper = insuranceMapper;
    }

    @GetMapping("/insurances")
    List<Insurance> all() {
        return insuranceService.findAll();
    }

    @PostMapping("/insurances")
    Insurance newInsurance(@RequestBody Insurance newInsurance) {
        return insuranceService.save(newInsurance);
    }

    @GetMapping("/insurances/{id}")
    Insurance one(@PathVariable int id) {

        return insuranceService.findInsuranceById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @PutMapping("/insurances/{id}")
    Insurance replaceInsurance(@RequestBody Insurance newInsurance, @PathVariable int id) {
        return insuranceService.findInsuranceById(id)
                .map(insurance -> {
                    insurance.setEmailAgency(newInsurance.getEmailAgency());
                    insurance.setExpires(newInsurance.getExpires());
                    insurance.setGreenCardNumber(newInsurance.getGreenCardNumber());
                    insurance.setInsuranceNumber(newInsurance.getInsuranceNumber());
                    insurance.setInsurer(newInsurance.getInsurer());
                    insurance.setPhoneAgency(newInsurance.getPhoneAgency());

                    return insuranceService.save(insurance);
                })
                .orElseGet(() -> {
                    newInsurance.setId(id);
                    return insuranceService.save(newInsurance);
                });
    }

    @DeleteMapping("/insurances/{id}")
    void deleteInsurance(@PathVariable int id) {
        insuranceService.deleteById(id);
    }

}
