package com.realdolmen.EuropeanHub.insurer;

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
public class InsuranceController {

    private final InsuranceService insuranceService;

    InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
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
