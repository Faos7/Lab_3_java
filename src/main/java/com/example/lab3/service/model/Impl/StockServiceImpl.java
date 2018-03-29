package com.example.lab3.service.model.Impl;

import com.example.lab3.domain.model.Item;
import com.example.lab3.domain.model.Stock;
import com.example.lab3.domain.model.dummies_forms.StockForm;
import com.example.lab3.repository.model.CityRepository;
import com.example.lab3.repository.model.ItemRepository;
import com.example.lab3.repository.model.StockRepository;
import com.example.lab3.service.model.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockService.class);

    private final StockRepository stockRepository;
    private final CityRepository cityRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository, CityRepository cityRepository, ItemRepository itemRepository) {
        this.stockRepository = stockRepository;
        this.cityRepository = cityRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public Collection<Stock> getAllStocks() {
        LOGGER.debug("Getting all stocks");
        return stockRepository.findAll(new Sort("number"));
    }

    @Override
    public boolean remove(Integer id) {
        Stock stock = stockRepository.getOne(id);
        List<Item> itemList = itemRepository.findAll();
        for (Item item : itemList) {
            if (item.getStock().equals(stock)) {
                return false;
            }
        }
        stockRepository.delete(stock);
        return true;
    }

    @Override
    public Stock create(StockForm stockForm) {
        Stock stock = new Stock();
        stock.setAdres(stockForm.getAdres());
        stock.setCity(cityRepository.findOneByName(stockForm.getCity()).get());
        stock.setNumber(stockForm.getNumber());
        stock.setPhone(stockForm.getPhone());
        stock.setSite(stockForm.getSite());

        return stockRepository.save(stock);
    }

    @Override
    public Collection<Stock> getAllStocksWithNumber(Integer number) {
        return stockRepository.getAllByNumber(number);
    }

    @Override
    public void delete(Integer id) {
        stockRepository.delete(stockRepository.getOne(id));
    }
}
