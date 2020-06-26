package com.codegym.validations;


import com.codegym.aspects.Logger;
import com.codegym.models.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.sql.Timestamp;

public class NameValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;

        if (user.getName().equals("Bill Gates")) {
            errors.rejectValue("name", "name.invalid", "Ten nay khong hop le.");
        }

    }
}
