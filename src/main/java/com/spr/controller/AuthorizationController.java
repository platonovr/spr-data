package com.spr.controller;

import com.spr.model.User;
import com.spr.service.UserService;
import com.spr.validation.AuthorizationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;

/**
 * Created by Roman on 08.04.2014.
 */

@Controller
@RequestMapping("/login")
@SessionAttributes("userLogin")
public class AuthorizationController {
    private final UserService userService;


    @Autowired
    private AuthorizationValidator authorizationValidator;

    @Autowired
    public AuthorizationController(UserService userService) {
        this.userService = userService;
    }


    @InitBinder
    private void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof User) {

            binder.setValidator(authorizationValidator);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAuthorizationForm(Map model, SessionStatus status) {
        status.setComplete();
        model.put("login", "");
        model.put("password", "");
        return "authorization";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String authorization(@RequestParam("login") String login, @RequestParam("password") String password, Model model) {
        if (userService.getUserByLogin(login) == null) {
            model.addAttribute("message", "Wrong login ");

            return "authorization";
        } else {
            if (userService.getUserByLogin(login).getPassword().equals(password)) {

                model.addAttribute("userLogin", login);
                return "redirect:/registration/users/" + userService.getUserByLogin(login).getId();
            } else {
                model.addAttribute("message", "Wrong password");
                return "authorization";
            }
        }

    }
}
