package com.aaonrajan.RideSharingDeliveryApp.controller;

import com.aaonrajan.RideSharingDeliveryApp.model.User;
import com.aaonrajan.RideSharingDeliveryApp.model.UserDto;
import com.aaonrajan.RideSharingDeliveryApp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @Operation(summary = "Creating new user")
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @Operation(summary = "Getting all users")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Operation(summary = "Get user by ID")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @Operation(summary = "Updating user by ID")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(userDto, id));
    }

    @Operation(summary = "Deleting user by ID")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    @Operation(summary = "Setting vehicle to user")
    @PutMapping("/setVehicle")
    public void setVehicle(@RequestParam long userId, @RequestParam long vehicleId) {
        userService.setVehicle(userId, vehicleId);
    }
}
