package com.example.lab3.validator;

import com.example.lab3.domain.model.dummies_forms.CityForm;
import com.example.lab3.service.model.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CityCreateFormValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityCreateFormValidator.class);
    private final CityService cityService;

    @Autowired
    public CityCreateFormValidator(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(CityForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LOGGER.debug("Validating {}", target);
        CityForm form = (CityForm) target;
        validateName(errors, form);
    }

    private void validateName(Errors errors, CityForm form) {
        if (cityService.getOneByName(form.getName()).isPresent()){
            errors.reject("name.exists", "City with this name already exists");
        }
    }
}
