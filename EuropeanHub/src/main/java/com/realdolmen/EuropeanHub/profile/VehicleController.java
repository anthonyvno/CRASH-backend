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
public class VehicleController {

    private final VehicleRepository vehicleRepository;

    VehicleController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @GetMapping("/vehicles")
    List<Vehicle> all() {
        return vehicleRepository.findAll();
    }

    @PostMapping("/vehicles")
    Vehicle newVehicle(@RequestBody Vehicle newVehicle) {
        return vehicleRepository.save(newVehicle);
    }

    @GetMapping("/vehicles/{id}")
    Vehicle one(@PathVariable int id) {

        return vehicleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @PutMapping("/vehicles/{id}")
    Vehicle replaceVehicle(@RequestBody Vehicle newVehicle, @PathVariable int id) {

        return vehicleRepository.findById(id)
                .map(vehicle -> {
                    vehicle.setCountry(newVehicle.getCountry());
                    vehicle.setLicensePlate(newVehicle.getLicensePlate());
                    vehicle.setBrand(newVehicle.getBrand());
                    vehicle.setModel(newVehicle.getModel());
                    vehicle.setType(newVehicle.getType());
                    return vehicleRepository.save(vehicle);
                })
                .orElseGet(() -> {
                    newVehicle.setId(id);
                    return vehicleRepository.save(newVehicle);
                });
    }

    @DeleteMapping("/vehicles/{id}")
    void deleteVehicle(@PathVariable int id) {
        vehicleRepository.deleteById(id);
    }

}
