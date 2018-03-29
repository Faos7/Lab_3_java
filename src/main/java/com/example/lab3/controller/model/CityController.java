package com.example.lab3.controller.model;

import com.example.lab3.domain.model.dummies_forms.CityForm;
import com.example.lab3.service.model.CityService;
import com.example.lab3.validator.CityCreateFormValidator;
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

@Controller
public class CityController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityController.class);

    private final CityService cityService;
    private final CityCreateFormValidator cityCreateFormValidator;

    @Autowired
    public CityController(CityService cityService, CityCreateFormValidator cityCreateFormValidator) {
        this.cityService = cityService;
        this.cityCreateFormValidator = cityCreateFormValidator;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(cityCreateFormValidator);
    }

    @RequestMapping("/cities")
    public ModelAndView getCitiesPage() {
        LOGGER.debug("Getting cities page");
        return new ModelAndView("cities", "cities", cityService.getAllCities());
    }

    @RequestMapping(value = "/city/create", method = RequestMethod.GET)
    public ModelAndView getCityCreatePage() {
        LOGGER.debug("Getting city create form");
        return new ModelAndView("city_create", "form", new CityForm());
    }

    @RequestMapping("/city/remove/{id}")
    public String deleteCity(@PathVariable Integer id){
        LOGGER.debug("removing city with id={}", id);

        if (!cityService.remove(id))

            return "redirect:/exception";

        return "redirect:/cities";
    }

    @RequestMapping(value = "/city/create", method = RequestMethod.POST)
    public String handleCityCreateForm(@Valid @ModelAttribute("form") CityForm form, BindingResult bindingResult) {
        LOGGER.debug("Processing city create form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            // failed validation
            return "redirect:city/create";
        }
        try {
            cityService.create(form);
        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("Exception occurred when trying to save the city, assuming duplicate name", e);
            bindingResult.reject("name.exists", "City with such name already exists");
            return "redirect:city/create";
        }
        // ok, redirect
        return "redirect:/cities";
    }
}
