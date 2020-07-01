package com.codegym.controllers;

import com.codegym.aspects.Logger;
import com.codegym.models.User;
import com.codegym.validations.NameValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;

@Controller
public class UserController {

    @Autowired
    HttpSession session;

    @GetMapping("/set-session")
    public String demoSetSession() {
        session.setAttribute("MY_BIRTHYEAR", "1989");
        return "redirect:/";
    }

    @GetMapping("/get-session")
    public String demoGetSession(Model model) {
        Object sessionValue = session.getAttribute("MY_BIRTHYEAR");
        String myBirthyear = (sessionValue == null) ? "" : sessionValue.toString();
        model.addAttribute("myBirthyear", myBirthyear);

        return "get-session";
    }

    @GetMapping("/user")
    public ModelAndView showForm(
            @CookieValue(value = "MY_BIRTHYEAR", defaultValue = "") String birthYear,
            @CookieValue(value="myName", defaultValue = "") String fullname,
            HttpServletResponse response
            ) {

        Cookie cookie = new Cookie("MY_BIRTHYEAR", "1989");
        cookie.setMaxAge(24*60*60);
        response.addCookie(cookie);


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
