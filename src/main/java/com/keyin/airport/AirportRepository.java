package com.keyin.airport;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Long> {

    // Method to find airports by city ID
    List<Airport> findByCityId(Long cityId);
}
