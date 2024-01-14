package com.example.TravelAgencyApi.services;

import com.example.TravelAgencyApi.DTOs.CreateHolidayDTO;
import com.example.TravelAgencyApi.DTOs.ResponseHolidayDTO;
import com.example.TravelAgencyApi.DTOs.UpdateHolidayDTO;
import com.example.TravelAgencyApi.entities.Holiday;
import com.example.TravelAgencyApi.exeptions.HolidayNotFoundException;
import com.example.TravelAgencyApi.repositories.HolidayRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.Location;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class HolidayService {

    @Autowired
    private HolidayRepository holidayRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseHolidayDTO createHoliday(CreateHolidayDTO createHolidayDTO) {
        Holiday holiday = new Holiday();
        holiday.setDuration(createHolidayDTO.getDuration());
        holiday.setTitle(createHolidayDTO.getTitle());
        holiday.setFreeSlots(createHolidayDTO.getFreeSlots());
        holiday.setPrice(createHolidayDTO.getPrice());
        holiday.setStartDate(createHolidayDTO.getStartDate());
        holidayRepository.save(holiday);
        return modelMapper.map(holiday, ResponseHolidayDTO.class);
    }

    public ResponseHolidayDTO getHolidayById(Long id) throws HolidayNotFoundException {
        Holiday holiday = holidayRepository.findById(id).orElseThrow(() -> new HolidayNotFoundException("Holiday not found for id = " + id));
        return modelMapper.map(holiday, ResponseHolidayDTO.class);
    }

    public List<ResponseHolidayDTO> getAllHoliday() {
        List<Holiday> holidays = holidayRepository.findAll();
        return holidays.stream().map(holiday -> modelMapper.map(holiday, ResponseHolidayDTO.class)).collect(Collectors.toList());
    }

    public void deleteHolidayById(Long id) throws HolidayNotFoundException {
        Holiday holiday = holidayRepository.findById(id).orElseThrow(() -> new HolidayNotFoundException("Holiday not found for id = " + id));
        holidayRepository.delete(holiday);
    }

    public ResponseHolidayDTO updateHoliday(UpdateHolidayDTO updateHoliday) throws HolidayNotFoundException {
        Holiday holiday = holidayRepository.findById(updateHoliday.getId()).orElseThrow(() -> new HolidayNotFoundException("Holiday not found for id = " + updateHoliday.getId()));

        holiday.setDuration(updateHoliday.getDuration());
        holiday.setTitle(updateHoliday.getTitle());
        holiday.setFreeSlots(updateHoliday.getFreeSlots());
        holiday.setPrice(updateHoliday.getPrice());
        holiday.setStartDate(updateHoliday.getStartDate());
        holidayRepository.save(holiday);

        return modelMapper.map(holiday, ResponseHolidayDTO.class);
    }

}
