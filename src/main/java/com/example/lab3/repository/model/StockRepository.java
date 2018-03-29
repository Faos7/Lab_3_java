package com.example.lab3.repository.model;

import com.example.lab3.domain.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Integer> {

    Collection<Stock> getAllByNumber(Integer number);

    Optional<Stock> getByCity_NameAndNumber(String city_name, Integer number);
}
