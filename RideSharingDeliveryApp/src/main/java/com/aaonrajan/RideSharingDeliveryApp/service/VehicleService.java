package com.aaonrajan.RideSharingDeliveryApp.service;

import com.aaonrajan.RideSharingDeliveryApp.model.*;
import com.aaonrajan.RideSharingDeliveryApp.repository.VehicleRespoitory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    private final VehicleRespoitory vehicleRepo;

    public VehicleService(VehicleRespoitory vehicleRepo) {
        super();
        this.vehicleRepo = vehicleRepo;
    }

    public Vehicle createVehicle(VehicleDto vehicleDto) {
        for (Vehicle v: vehicleRepo.findAll()) {
            if (v.getPlateNo().equals(vehicleDto.getPlateNo())) {
                throw new RuntimeException("A vehicle with the plate number " +
                        vehicleDto.getPlateNo() + " already exists");
            }
        }
        Vehicle vehicle = null;
        if (vehicleDto.getVehicleType().equals("car")) {
            vehicle = new Car();
        } else if (vehicleDto.getVehicleType().equals("bike")) {
            vehicle = new Bike();
        } else if (vehicleDto.getVehicleType().equals("truck")) {
            vehicle = new Truck();
        }
        BeanUtils.copyProperties(vehicleDto, vehicle);
        return vehicleRepo.save(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepo.findAll(Sort.by("vehicleId"));
    }

    public Vehicle getVehicle(long id) {
        return vehicleRepo.findById(id).get();
    }

    public Vehicle updateVehicle(VehicleDto vehicleDto, long id) {
        Optional<Vehicle> optVehicle = vehicleRepo.findById(id);
        Vehicle vehicleToBeUpdated = null;
        if (optVehicle.isPresent()) {
            vehicleToBeUpdated = optVehicle.get();
        } else {
            if (vehicleDto.getVehicleType().equals("car")) {
                vehicleToBeUpdated = new Car();
            } else if (vehicleDto.getVehicleType().equals("bike")) {
                vehicleToBeUpdated = new Bike();
            } else if (vehicleDto.getVehicleType().equals("truck")) {
                vehicleToBeUpdated = new Truck();
            }
        }
        BeanUtils.copyProperties(vehicleDto, vehicleToBeUpdated);
        return vehicleRepo.save(vehicleToBeUpdated);
    }

    public void deleteVehicle(long id) {
        vehicleRepo.deleteById(id);
    }


}
