package com.realdolmen.EuropeanHub.profile;

import com.realdolmen.EuropeanHub.common.NotFoundException;
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
public class VehicleController {

    private final VehicleService vehicleService;

    VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/vehicles")
    List<Vehicle> all() {
        return vehicleService.findAll();
    }

    @PostMapping("/vehicles")
    Vehicle newVehicle(@RequestBody Vehicle newVehicle) {
        return vehicleService.save(newVehicle);
    }

    @GetMapping("/vehicles/{id}")
    Vehicle one(@PathVariable int id) {

        return vehicleService.findVehicleById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @PutMapping("/vehicles/{id}")
    Vehicle replaceVehicle(@RequestBody Vehicle newVehicle, @PathVariable int id) {

        return vehicleService.findVehicleById(id)
                .map(vehicle -> {
                    vehicle.setCountry(newVehicle.getCountry());
                    vehicle.setLicensePlate(newVehicle.getLicensePlate());
                    vehicle.setBrand(newVehicle.getBrand());
                    vehicle.setModel(newVehicle.getModel());
                    vehicle.setType(newVehicle.getType());
                    vehicle.setInsurance(newVehicle.getInsurance());
                    return vehicleService.save(vehicle);
                })
                .orElseGet(() -> {
                    newVehicle.setId(id);
                    return vehicleService.save(newVehicle);
                });
    }

    @DeleteMapping("/vehicles/{id}")
    void deleteVehicle(@PathVariable int id) {
        vehicleService.deleteById(id);
    }

}
