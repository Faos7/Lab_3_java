package com.example.lab3.repository.model;

import com.example.lab3.domain.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {
    Optional<City> findOneByName(String name);
}
