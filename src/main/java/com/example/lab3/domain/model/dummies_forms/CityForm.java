package com.example.lab3.domain.model.dummies_forms;

import org.hibernate.validator.constraints.NotEmpty;

public class CityForm {

    @NotEmpty
    private String name = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
