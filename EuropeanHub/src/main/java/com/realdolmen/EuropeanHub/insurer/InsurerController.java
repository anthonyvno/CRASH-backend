
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
public class InsurerController {

    private final InsurerService insurerService;

    InsurerController(InsurerService insurerService) {
        this.insurerService = insurerService;
    }

    @GetMapping("/insurers")
    List<Insurer> all() {
        return insurerService.findAll();
    }

    @PostMapping("/insurers")
    Insurer newInsurer(@RequestBody Insurer newInsurer) {
        return insurerService.save(newInsurer);
    }

    @GetMapping("/insurers/{id}")
    Insurer one(@PathVariable int id) {

        return insurerService.findInsurerById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @PutMapping("/insurers/{id}")
    Insurer replaceInsurer(@RequestBody Insurer newInsurer, @PathVariable int id) {
        return insurerService.findInsurerById(id)
                .map(insurer -> {
                    insurer.setName(newInsurer.getName());
                    insurer.setCountry(newInsurer.getCountry());
                    return insurerService.save(insurer);
                })
                .orElseGet(() -> {
                    newInsurer.setId(id);
                    return insurerService.save(newInsurer);
                });
    }

    @DeleteMapping("/insurers/{id}")
    void deleteInsurer(@PathVariable int id) {
        insurerService.deleteById(id);
    }

}
