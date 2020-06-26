package com.codegym.controllers;

import com.codegym.aspects.Logger;
import com.codegym.models.User;
import com.codegym.validations.NameValidation;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.Date;

@Controller
public class UserController {

    @GetMapping("/user")
    public ModelAndView showForm() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/validateUser")
    public ModelAndView checkValidation(@Validated @ModelAttribute("user") User user, BindingResult bindingResult) {

        NameValidation nameValidation = new NameValidation();

        if (nameValidation.supports(user.getClass())) {
            nameValidation.validate(user, bindingResult);
        }

        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("index");
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView("result");
        return modelAndView;
    }

}
