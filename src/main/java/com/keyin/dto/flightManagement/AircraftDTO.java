package com.keyin.dto.flightManagement;

public class AircraftDTO {
    private Long id;
    private String type;
    private int capacity;

    // Constructor
    public AircraftDTO(Long id, String type, int capacity) {
        this.id = id;
        this.type = type;
        this.capacity = capacity;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
}