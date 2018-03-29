package com.example.lab3.service.model;

import com.example.lab3.domain.model.Stock;
import com.example.lab3.domain.model.dummies_forms.StockForm;

import java.util.Collection;

public interface StockService {

    Stock create(StockForm stockForm);

    Collection<Stock> getAllStocks();

    Collection<Stock> getAllStocksWithNumber(Integer number);

    boolean remove(Integer id);

    void delete(Integer id);
}
