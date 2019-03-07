
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

    private final InsurerRepository insurerRepository;

    InsurerController(InsurerRepository insurerRepository) {
        this.insurerRepository = insurerRepository;
    }

    @GetMapping("/insurers")
    List<Insurer> all() {
        return insurerRepository.findAll();
    }

    @PostMapping("/insurers")
    Insurer newInsurer(@RequestBody Insurer newInsurer) {
        return insurerRepository.save(newInsurer);
    }

    @GetMapping("/insurers/{id}")
    Insurer one(@PathVariable int id) {

        return insurerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @PutMapping("/insurers/{id}")
    Insurer replaceInsurer(@RequestBody Insurer newInsurer, @PathVariable int id) {
        return insurerRepository.findById(id)
                .map(insurer -> {
                    insurer.setName(newInsurer.getName());
                    insurer.setCountry(newInsurer.getCountry());
                    return insurerRepository.save(insurer);
                })
                .orElseGet(() -> {
                    newInsurer.setId(id);
                    return insurerRepository.save(newInsurer);
                });
    }

    @DeleteMapping("/insurers/{id}")
    void deleteInsurer(@PathVariable int id) {
        insurerRepository.deleteById(id);
    }

}
