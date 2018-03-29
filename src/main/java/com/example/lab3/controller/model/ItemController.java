package com.example.lab3.controller.model;

import com.example.lab3.domain.model.Item;
import com.example.lab3.domain.model.Stock;
import com.example.lab3.domain.model.dummies_forms.ItemForm;
import com.example.lab3.service.model.ItemService;
import com.example.lab3.service.model.StockService;
import com.example.lab3.validator.ItemCreateFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ItemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);


    private final ItemService itemService;
    private final StockService stockService;
    private final ItemCreateFormValidator itemCreateFormValidator;

    @Autowired
    public ItemController(ItemService itemService, StockService stockService, ItemCreateFormValidator itemCreateFormValidator) {
        this.itemService = itemService;
        this.stockService = stockService;
        this.itemCreateFormValidator = itemCreateFormValidator;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(itemCreateFormValidator);
    }

    @RequestMapping("/items")
    public ModelAndView getItemsPage() {
        LOGGER.debug("Getting items page");
        return new ModelAndView("items", "items", itemService.getAllItems());
    }

    @RequestMapping("/item/remove/{id}")
    public String deleteItem(@PathVariable Integer id){
        LOGGER.debug("removing item with id={}", id);
        itemService.delete(id);
        return "redirect:/items";
    }

    @RequestMapping(value = "/item/create", method = RequestMethod.GET)
    public ModelAndView getItemCreatePage() {
        LOGGER.debug("Getting item create form");
        ModelAndView mav = new ModelAndView("item_create", "form", new ItemForm());
        List<Stock> stocks = new ArrayList<>();
        stocks.addAll(stockService.getAllStocks());
        mav.addObject("stocks", stocks);
        return mav;
    }

    @RequestMapping(value = "/item/create", method = RequestMethod.POST)
    public String handleItemCreateForm(@Valid @ModelAttribute("form") ItemForm form, BindingResult bindingResult) {
        LOGGER.debug("Processing item create form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            // failed validation
            LOGGER.warn("Exception occurred when trying to validate  the item");
            return "redirect:/item/create";
        }
        try {
            Item item = itemService.create(form);
            if (item == null){
                return "redirect:/item/create";
            }
        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("Exception occurred when trying to save the item, assuming duplicate name", e);
            bindingResult.reject("name.exists", "Item with such name already exists");
            return "redirect:/item/create";
        }
        // ok, redirect
        return "redirect:/items";
    }


}
