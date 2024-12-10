package com.keyin.dto.flightManagement;

public class AirportDTO {
    private Long id;
    private String code;
    private String city;

    public AirportDTO(Long id, String code, String city) {
        this.id = id;
        this.code = code;
        this.city = city;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
}
