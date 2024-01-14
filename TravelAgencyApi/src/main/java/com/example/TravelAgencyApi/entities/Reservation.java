package com.example.TravelAgencyApi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Table(name = "reservations")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String contactName;

    @NotNull
    private String phoneNumber;

    @ManyToOne
    private Holiday holiday;
    @NotNull
    private String customerName;

    public void setCustomerName(Object customerName) {
        this.customerName = (String) customerName;
    }


    public void setHoliday(Holiday holiday) {
        this.holiday= holiday;
    }


}
