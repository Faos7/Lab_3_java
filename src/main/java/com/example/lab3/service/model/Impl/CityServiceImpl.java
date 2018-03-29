package com.example.lab3.service.model.Impl;

import com.example.lab3.domain.model.City;
import com.example.lab3.domain.model.Stock;
import com.example.lab3.domain.model.dummies_forms.CityForm;
import com.example.lab3.repository.model.CityRepository;
import com.example.lab3.repository.model.StockRepository;
import com.example.lab3.service.model.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityService.class);

    private final CityRepository cityRepository;
    private final StockRepository stockRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, StockRepository stockRepository) {
        this.cityRepository = cityRepository;
        this.stockRepository = stockRepository;
    }

    @Override
    public Collection<City> getAllCities() {
        LOGGER.debug("Getting all cities");
        return cityRepository.findAll(new Sort("name"));
    }

    @Override
    public City create(CityForm cityForm) {
        City city = new City();
        city.setName(cityForm.getName());
        return cityRepository.save(city);
    }

    @Override
    public Optional<City> getOneByName(String name) {
        return cityRepository.findOneByName(name);
    }

    @Override
    public boolean remove(Integer id) {
        City city = cityRepository.getOne(id);
        List<Stock> stockList = stockRepository.findAll();
        for (Stock s :
                stockList) {
            if (s.getCity().equals(city)) {
                return false;
            }
        }
        cityRepository.delete(city);
        return true;
    }

    @Override
    public void delete(Integer id) {
        cityRepository.delete(cityRepository.getOne(id));
    }
}
