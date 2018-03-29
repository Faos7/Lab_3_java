package com.example.lab3.service.model;

import com.example.lab3.domain.model.City;
import com.example.lab3.domain.model.dummies_forms.CityForm;

import java.util.Collection;
import java.util.Optional;


public interface CityService {

    Collection<City> getAllCities();

    City create(CityForm cityForm);

    Optional<City> getOneByName(String name);

    boolean remove(Integer id);

    void delete(Integer id);
}
