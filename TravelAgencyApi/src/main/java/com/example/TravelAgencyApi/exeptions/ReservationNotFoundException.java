package com.example.TravelAgencyApi.exeptions;

public class ReservationNotFoundException extends Exception {
    public ReservationNotFoundException(Long id) {
    }

    public ReservationNotFoundException(String errorMessage) {
    }
}
