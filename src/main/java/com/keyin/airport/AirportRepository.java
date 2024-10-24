package com.keyin.airport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Long> {

    // Method to find airports by city ID
    List<Airport> findByCityId(Long cityId);

    @Query("SELECT DISTINCT a FROM Airport a JOIN a.supportedAircraftTypes t WHERE t = :aircraftType")
    List<Airport> findByAircraftType(@Param("aircraftType") String aircraftType);
}
