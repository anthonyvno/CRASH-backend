
package com.realdolmen.EuropeanHub.insurer;

import com.realdolmen.EuropeanHub.common.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
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
public class InsurerController {

    private final InsurerService insurerService;
    private final InsurerMapper insurerMapper;

    InsurerController(InsurerService insurerService,InsurerMapper insurerMapper) {
        this.insurerService = insurerService;
        this.insurerMapper = insurerMapper;
    }

    @GetMapping("/insurers")
    List<InsurerDTO> all() {
        
        return insurerService.findAll()
                .stream()
                .map(insurerMapper::mapInsurerToDTO)
                    .collect(Collectors.toList());
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
