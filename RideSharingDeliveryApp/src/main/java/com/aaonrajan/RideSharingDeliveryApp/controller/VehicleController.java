package com.aaonrajan.RideSharingDeliveryApp.controller;

import com.aaonrajan.RideSharingDeliveryApp.model.Vehicle;
import com.aaonrajan.RideSharingDeliveryApp.model.VehicleDto;
import com.aaonrajan.RideSharingDeliveryApp.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        super();
        this.vehicleService = vehicleService;
    }

    @Operation(summary = "Creating new vehicle")
    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody VehicleDto vehicleDto) {
        return ResponseEntity.ok(vehicleService.createVehicle(vehicleDto));
    }

    @Operation(summary = "Getting all vehicles")
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @Operation(summary = "Getting vehicle by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable long id) {
        return ResponseEntity.ok(vehicleService.getVehicle(id));
    }

    @Operation(summary = "Updating vehicle by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable long id, @Valid @RequestBody VehicleDto vehicleDto) {
        return ResponseEntity.ok(vehicleService.updateVehicle(vehicleDto, id));
    }

    @Operation(summary = "Deleting vehicle by ID")
    @DeleteMapping("/{id}")
    public void deletVehicle(@PathVariable long id) {
        vehicleService.deleteVehicle(id);
    }
}
