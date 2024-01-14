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
public class ResponseReservationDTO {

    private Long id;
    private String customerName;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<ResponseLocationDTO> locations;
    private LocalDate lastUpdated;

}
