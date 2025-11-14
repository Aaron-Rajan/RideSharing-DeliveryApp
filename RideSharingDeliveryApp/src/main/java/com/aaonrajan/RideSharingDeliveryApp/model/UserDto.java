package com.aaonrajan.RideSharingDeliveryApp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

public class UserDto {
    @Column
    @NotNull(message = "First name is required")
    private String firstName;
    @Column
    @NotNull(message = "Last name is required")
    private String lastName;
    @Column
    @NotNull(message = "Email is required")
    @Email(message = "Email must be a valid email address")
    private String email;
    @Column
    @NotNull(message = "Phone number is required")
    @Pattern(
            regexp = "^\\+?[0-9 .()-]{7,20}$",
            message = "Phone number is invalid"
    )
    private String phoneNo;
    @JsonProperty("active")
    private boolean isActive;
    @Column
    @NotNull(message = "Balance is required")
    @PositiveOrZero(message = "Balance must be zero or positive")
    private double balance;
    @Column
    @NotNull(message = "Password is required")
    @Size(min = 8, max = 64,
            message = "Password must be between 8 and 64 characters")
    private String password;
    @Column
    @NotNull(message = "User type is required")
    @Pattern(regexp = "driver|rider|dispatcher", flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "User type must be 'driver', 'rider', or 'dispatcher'")
    private String userType;

    public UserDto() {
        super();
    }

    public UserDto(String firstName, String lastName, String email, String phoneNo, boolean isActive, double balance, String password, String userType) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.isActive = isActive;
        this.balance = balance;
        this.password = password;
        this.userType = userType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
