package com.keyin.cities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Cities {
    @Id
    @SequenceGenerator(name = "division_sequence", sequenceName = "division_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "division_sequence")
    private long id;

    private String name;

    private String province;

    private int population;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public Cities() {
    }

    public Cities(long id, String name, String province, int population) {
        this.id = id;
        this.name = name;
        this.province = province;
        this.population = population;
    }

    public Cities(String name, String province, int population) {
        this.name = name;
        this.province = province;
        this.population = population;
    }
}
