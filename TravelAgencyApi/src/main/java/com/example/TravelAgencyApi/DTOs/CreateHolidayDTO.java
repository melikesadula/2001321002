package com.example.TravelAgencyApi.DTOs;

import com.example.TravelAgencyApi.entities.Holiday;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateHolidayDTO {
    private Long location;
    private String title;
    private Date startDate;
    private int duration;
    private String price;
    private int freeSlots;


    public Holiday toHoliday() {
        return null;
    }
}
