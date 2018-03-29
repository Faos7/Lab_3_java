package com.example.lab3.service.model;

import com.example.lab3.domain.model.Item;
import com.example.lab3.domain.model.dummies_forms.ItemForm;

import java.util.Collection;

public interface ItemService {

    Collection<Item> getAllItems();

    boolean remove (Item item);

    Item create(ItemForm itemForm);

    void delete(Integer id);
}
