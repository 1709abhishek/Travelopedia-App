package com.travelopedia.fun.customer_service.accounts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelopedia.fun.customer_service.accounts.models.Profile;
import com.travelopedia.fun.customer_service.accounts.service.ProfileService;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/{accountId}")
    public Profile getProfile(@PathVariable int accountId) {
        return profileService.getProfileData(accountId);
    }
}
