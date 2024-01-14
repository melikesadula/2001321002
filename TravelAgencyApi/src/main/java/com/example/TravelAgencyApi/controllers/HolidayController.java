package com.example.TravelAgencyApi.controllers;

import com.example.TravelAgencyApi.DTOs.CreateHolidayDTO;
import com.example.TravelAgencyApi.DTOs.UpdateHolidayDTO;
import com.example.TravelAgencyApi.DTOs.ResponseHolidayDTO;
import com.example.TravelAgencyApi.entities.Holiday;
import com.example.TravelAgencyApi.repositories.HolidayRepository;
import com.example.TravelAgencyApi.services.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/holidays")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

public class HolidayController{

        @Autowired
        private HolidayRepository holidayRepository;

        @GetMapping
        public List<Holiday> getAllHolidays() {
            return holidayRepository.findAll();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Holiday> getHolidayById(@PathVariable Long id) {
            Optional<Holiday> holiday = holidayRepository.findById(id);
            if (holiday.isPresent()) {
                return ResponseEntity.ok(holiday.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @PostMapping
        public Holiday createHoliday(@RequestBody Holiday holiday) {
            return holidayRepository.save(holiday);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Holiday> updateHoliday(@PathVariable Long id, @RequestBody Holiday updatedHoliday) {
            Optional<Holiday> existingHoliday = holidayRepository.findById(id);
            if (existingHoliday.isPresent()) {
                updatedHoliday.setId(id);
                return ResponseEntity.ok(holidayRepository.save(updatedHoliday));
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteHoliday(@PathVariable Long id) {
            Optional<Holiday> holiday = holidayRepository.findById(id);
            if (holiday.isPresent()) {
                holidayRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }

}