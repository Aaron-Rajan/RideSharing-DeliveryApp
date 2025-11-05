package com.aaonrajan.RideSharingDeliveryApp.model;

import jakarta.persistence.*;

@Entity
@Table(name="DISPATCHER")
public class Dispatcher extends User {

    public Dispatcher(long userId, String firstName, String lastName, String email, String phoneNo, boolean isActive) {
        super(userId, firstName, lastName, email, phoneNo, isActive);
    }
}
