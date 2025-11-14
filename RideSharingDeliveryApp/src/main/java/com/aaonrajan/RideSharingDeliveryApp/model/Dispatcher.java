package com.aaonrajan.RideSharingDeliveryApp.model;

import jakarta.persistence.*;

@Entity
@Table(name="DISPATCHER")
public class Dispatcher extends User {

    public Dispatcher() {
        super();
    }

    public Dispatcher(long userId, String firstName, String lastName, String email, String phoneNo, boolean isActive, String password, Role role) {
        super(userId, firstName, lastName, email, phoneNo, isActive, password, role);
    }
}
