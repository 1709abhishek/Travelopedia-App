package com.travelopedia.fun.customer_service.accounts.models;

public class Profile {
    private int accountId;
    private double totalDistanceTraveled;
    private int numberOfCountriesVisited;
    private double globalTravelRanking;

    public Profile(int accountId, double totalDistanceTraveled, int numberOfCountriesVisited, double globalTravelRanking) {
        this.accountId = accountId;
        this.totalDistanceTraveled = totalDistanceTraveled;
        this.numberOfCountriesVisited = numberOfCountriesVisited;
        this.globalTravelRanking = globalTravelRanking;
    }

    public int getAccountId() {
        return accountId;
    }

    public double getTotalDistanceTraveled() {
        return totalDistanceTraveled;
    }

    public int getNumberOfCountriesVisited() {
        return numberOfCountriesVisited;
    }

    public double getGlobalTravelRanking() {
        return globalTravelRanking;
    }
}
