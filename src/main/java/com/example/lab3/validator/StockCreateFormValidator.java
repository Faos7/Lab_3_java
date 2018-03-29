package com.example.lab3.validator;

import com.example.lab3.domain.model.Stock;
import com.example.lab3.domain.model.dummies_forms.StockForm;
import com.example.lab3.service.model.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Collection;

@Component
public class StockCreateFormValidator implements Validator{

    private static final Logger LOGGER = LoggerFactory.getLogger(StockCreateFormValidator.class);
    private final StockService stockService;

    @Autowired
    public StockCreateFormValidator(StockService stockService) {
        this.stockService = stockService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(StockForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LOGGER.debug("Validating {}", target);
        StockForm form = (StockForm) target;
        validateNumber(errors, form);
        validatePhone(errors,form);
    }

    private void validateNumber(Errors errors, StockForm form) {
        if (form.getNumber() < 0){
            errors.reject("number.wrong", "Stock number should be more then 0");
        }
        Collection<Stock> stocks = stockService.getAllStocksWithNumber(form.getNumber());
        for (Stock stock :
                stocks) {
            if (stock.getCity().getName().equals(form.getCity())){
                errors.reject("number.exists", "Stock with this number already exists in this city");
            }
        }
    }

    private void validatePhone(Errors errors, StockForm form){
        if (!regex(form.getPhone())){
            errors.reject("wrong.format", "Wrong phone number. Number should contain only numbers(0-9)");
        }
    }

    static boolean regex(final String text){
        for(int i=0; i<text.length(); i++) {
            if(text.charAt(i)<'0' || text.charAt(i)>'9') {
                return false;
            }
        }
        return true;
    }
}
