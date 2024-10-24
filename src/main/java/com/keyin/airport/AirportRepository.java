package com.keyin.airport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    // Method to find airports by city ID
    List<Airport> findByCityId(Long cityId);

    @Query("SELECT a FROM Airport a WHERE :aircraftType MEMBER OF a.supportedAircraftTypes")
    List<Airport> findByAircraftType(String aircraftType);
}