package com.example.TravelAgencyApi.repositories;

import com.example.TravelAgencyApi.entities.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.xml.stream.Location;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {

}