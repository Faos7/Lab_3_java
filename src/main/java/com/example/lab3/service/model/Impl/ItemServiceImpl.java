package com.example.lab3.service.model.Impl;

import com.example.lab3.domain.model.Item;
import com.example.lab3.domain.model.dummies_forms.ItemForm;
import com.example.lab3.repository.model.ItemRepository;
import com.example.lab3.repository.model.StockRepository;
import com.example.lab3.service.model.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class ItemServiceImpl implements ItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);

    private final ItemRepository itemRepository;
    private final StockRepository stockRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, StockRepository stockRepository) {
        this.itemRepository = itemRepository;
        this.stockRepository = stockRepository;
    }

    @Override
    public Collection<Item> getAllItems() {
        LOGGER.debug("Getting all items");
        return itemRepository.findAll(new Sort("name"));
    }

    @Override
    public boolean remove(Item item) {


        itemRepository.delete(itemRepository.findOne(item.getItemId()));
        return false;
    }

    @Override
    public Item create(ItemForm itemForm) {
        ArrayList<String> stockData = parseStockData(itemForm.getStock());
//        LOGGER.debug(stockData.get(0) + " " + stockData.get(1));
        if (stockRepository.getByCity_NameAndNumber(stockData.get(0),Integer.parseInt(stockData.get(1))).isPresent()) {
            Item item = new Item();
            item.setMinQuantity(itemForm.getMinQuantity());
            item.setName(itemForm.getName());
            item.setPrice(itemForm.getPrice());
            item.setProducer(itemForm.getProducer());
            item.setQuantity(itemForm.getQuantity());
            item.setStock(stockRepository.getByCity_NameAndNumber(stockData.get(0), Integer.parseInt(stockData.get(1))).get());
            return itemRepository.save(item);
        }
        return null;
    }

    public static ArrayList<String> parseStockData(String st){
        int i1 = st.indexOf(',');
        int i2 = st.indexOf('.');
        String s1 = st.substring(0,i1);

        String s2 = st.substring(i1+8,i2 );
        ArrayList<String> res = new ArrayList<>(2);
        res.add(s1);
        res.add(s2);
        return res;
    }

    @Override
    public void delete(Integer id) {
        itemRepository.delete(itemRepository.getOne(id));
    }
}
