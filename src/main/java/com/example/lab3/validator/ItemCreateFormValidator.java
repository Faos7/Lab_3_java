package com.example.lab3.validator;

import com.example.lab3.domain.model.dummies_forms.ItemForm;
import com.example.lab3.repository.model.StockRepository;
import com.example.lab3.service.model.Impl.ItemServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.ArrayList;

@Component
public class ItemCreateFormValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemCreateFormValidator.class);

    private final StockRepository repository;

    @Autowired
    public ItemCreateFormValidator(StockRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(ItemForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LOGGER.debug("Validating {}", target);
        ItemForm form = (ItemForm) target;
        validateStock(errors, form);
        validateQuantity(errors,form);
    }

    private void validateStock(Errors errors, ItemForm form){
        ArrayList<String> data = ItemServiceImpl.parseStockData(form.getStock());
        if (!repository.getByCity_NameAndNumber(data.get(0),Integer.parseInt(data.get(1))).isPresent()){
            errors.reject("wrong.data", "Stock with such city and number does not exist");
        }
    }

    private void validateQuantity(Errors errors, ItemForm form){
        if (form.getMinQuantity()>form.getQuantity()){
            errors.reject("wrong.quantity",
                    "Wrong quantity! Quantity can't be smaller then minimal Quantity)");
        }
    }

}
