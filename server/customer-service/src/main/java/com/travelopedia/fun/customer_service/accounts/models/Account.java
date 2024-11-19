package com.travelopedia.fun.customer_service.accounts.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

// import jakarta.annotation.Generated;

@Data
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String city;
    private String country;
    private String bio;
    private String favTravelQuote;
    private String placesTravelled;
    private String wishlist;
    private String profilePicUrl;

}