package com.keyin.city;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
public class CityControllerTest {


    @InjectMocks
    private CityController cityController;

    @Mock
    private CityServices cityServices;

    private City city;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        city = new City(1L, "SampleCity", "SampleProvince", 100000);
    }

    @Test
    void testGetAllCity() {
        List<City> cities = new ArrayList<>();
        cities.add(city);

        Mockito.when(cityServices.getAllCity()).thenReturn(cities);

        ResponseEntity<List<City>> response = cityController.getAllCity();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetCityById() {
        Mockito.when(cityServices.getAllCityById(anyLong())).thenReturn(city);

        ResponseEntity<City> response = cityController.getCityById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(city.getId(), response.getBody().getId());
        assertEquals(city.getName(), response.getBody().getName());
    }

    @Test
    void testAddCity() {
        Mockito.when(cityServices.addCity(any(City.class))).thenReturn(city);

        City newCity = new City("NewCity", "NewProvince", 50000);
        City createdCity = cityController.addCity(newCity);

        assertEquals(city.getName(), createdCity.getName());
        assertEquals(city.getProvince(), createdCity.getProvince());
    }

    @Test
    void testDeleteCity() {
        Mockito.doNothing().when(cityServices).deleteCityById(anyLong());

        cityController.deleteCity(1L);

        Mockito.verify(cityServices, Mockito.times(1)).deleteCityById(1L);
    }
}
