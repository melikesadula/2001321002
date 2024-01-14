package com.example.TravelAgencyApi.exeptions;

public class LocationNotFoundException extends Exception {
    public LocationNotFoundException(Long id) {
    }

    public LocationNotFoundException(String errorMessage) {
    }
}
