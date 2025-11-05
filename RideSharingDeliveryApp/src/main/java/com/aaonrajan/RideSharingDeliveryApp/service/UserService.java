package com.aaonrajan.RideSharingDeliveryApp.service;

import com.aaonrajan.RideSharingDeliveryApp.exceptions.UserNotFoundException;
import com.aaonrajan.RideSharingDeliveryApp.exceptions.VehicleNotFoundException;
import com.aaonrajan.RideSharingDeliveryApp.model.*;
import com.aaonrajan.RideSharingDeliveryApp.repository.UserRepository;
import com.aaonrajan.RideSharingDeliveryApp.repository.VehicleRespoitory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepo;
    private final VehicleRespoitory vehicleRepo;

    public UserService(UserRepository userRepo, VehicleRespoitory vehicleRepo) {
        super();
        this.userRepo = userRepo;
        this.vehicleRepo = vehicleRepo;
    }

    public User createUser(UserDto userDto) {
        User user = null;
        if (userDto.getUserType().equals("driver")) {
            user = new Driver();
        } else if (userDto.getUserType().equals("rider")) {
            user = new Rider();
        }
        BeanUtils.copyProperties(userDto, user, "isActive");
        user.setActive(true);
        return userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll(Sort.by("userId"));
    }

    public User getUser(long id) {
        return userRepo.findById(id).get();
    }

    public User updateUser(UserDto userDto, long id) {
        Optional<User> optUser = userRepo.findById(id);
        User userToBeUpdated = null;
        if (optUser.isPresent()) {
            userToBeUpdated = optUser.get();
        } else {
            if (userDto.getUserType().equals("driver")) {
                userToBeUpdated = new Driver();
            } else if (userDto.getUserType().equals("rider")) {
                userToBeUpdated = new Rider();
            }
        }
        BeanUtils.copyProperties(userDto, userToBeUpdated);
        return userRepo.save(userToBeUpdated);
    }

    public void deleteUser(long id) {
        userRepo.deleteById(id);
    }

    public void setVehicle(long userId, long vehicleId) {
        User userReceieved = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!(userReceieved instanceof Driver driverReceieved)) {
            throw new IllegalArgumentException("User with id " + userId + " is not a driver");
        }
        Vehicle vehicleReceieved = vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found"));
        driverReceieved.getVehicles().add(vehicleReceieved);
        vehicleReceieved.setDriver(driverReceieved);
        userRepo.save(driverReceieved);
    }
}
