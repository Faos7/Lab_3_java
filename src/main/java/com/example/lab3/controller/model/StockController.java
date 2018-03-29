package com.example.lab3.controller.model;

import com.example.lab3.domain.model.City;
import com.example.lab3.domain.model.dummies_forms.StockForm;
import com.example.lab3.service.model.CityService;
import com.example.lab3.service.model.StockService;
import com.example.lab3.validator.StockCreateFormValidator;
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
public class StockController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityController.class);

    private final StockService stockService;
    private final CityService cityService;
    private final StockCreateFormValidator stockCreateFormValidator;

    @Autowired
    public StockController(StockService stockService, CityService cityService,
                           StockCreateFormValidator stockCreateFormValidator) {
        this.stockService = stockService;
        this.cityService = cityService;
        this.stockCreateFormValidator = stockCreateFormValidator;
    }


    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(stockCreateFormValidator);
    }

    @RequestMapping("/stocks")
    public ModelAndView getStocksPage() {
        LOGGER.debug("Getting stocks page");
        return new ModelAndView("stocks", "stocks", stockService.getAllStocks());
    }

    @RequestMapping("/stock/remove/{id}")
    public String deleteStock(@PathVariable Integer id){
        LOGGER.debug("removing stock with id={}", id);
        if (!stockService.remove(id)){
            return "redirect:/exception";
        }
        return "redirect:/stocks";
    }

    @RequestMapping(value = "/stock/create", method = RequestMethod.GET)
    public ModelAndView getStockCreatePage() {
        LOGGER.debug("Getting stock create form");
        ModelAndView mav = new ModelAndView("stock_create", "form", new StockForm());
//        .addObject("cities",cityService.getAllCities());
        List<City> cities = new ArrayList<>();
        cities.addAll(cityService.getAllCities());
        mav.addObject("cities", cities);

        return mav;
    }

    @RequestMapping(value = "/stock/create", method = RequestMethod.POST)
    public String handleStockCreateForm(@Valid @ModelAttribute("form") StockForm form, BindingResult bindingResult) {
        LOGGER.debug("Processing stock create form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            // failed validation
            return "redirect:/stock/create";
        }
        try {
            stockService.create(form);
        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("Exception occurred when trying to save the stock, assuming duplicate name", e);
            bindingResult.reject("number.exists", "Stock with such number already exists");
            return "stock_create";
        }
        // ok, redirect
        return "redirect:/stocks";
    }
}