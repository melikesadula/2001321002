package com.example.TravelAgencyApi.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateReservationDTO {
    private String contactName;
    private String phoneNumber;
    private Long holiday;
    private Object customerName;
    private Iterable<Long> locationIds;

    public Iterable<Long> getLocationIds() {
        return locationIds;
    }

//    public Iterable<Long> getLocationIds() {
//        return null;
//    }
//
//    public Object getCustomerName() {
//        return customerName;
//    }
//
//    public void setCustomerName(Object customerName) {
//        this.customerName = customerName;
//    }


}
