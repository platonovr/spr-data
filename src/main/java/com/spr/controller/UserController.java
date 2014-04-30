package com.spr.controller;

import com.spr.model.User;
import com.spr.service.UserService;
import com.spr.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Controller
@SessionAttributes("userLogin")
@RequestMapping("/registration")

public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @Autowired
    private UserValidator userValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {

        if (binder.getTarget() instanceof User) {
            binder.setValidator(userValidator);
        }
    }


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView getRegistrationForm() {
        ModelAndView modelAndView = new ModelAndView("user-new", "user", new User());
        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView registration(@ModelAttribute @Valid User user, BindingResult result, final RedirectAttributes redirectAttributes) {


        if (result.hasErrors()) {

            return new ModelAndView("user-new");
        } else {
            String message = "user successfuly created";
            userService.createUser(user);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("redirect:/userProfile.html");
            redirectAttributes.addFlashAttribute("message", message);
            return modelAndView;

        }

    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView usersListPage() {
        ModelAndView mav = new ModelAndView("user-list");
        List<User> userList = (List<User>) userService.findAllUsers();
        Collections.sort(userList);
        mav.addObject("users", userList);
        return mav;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editUserPage(@PathVariable Long id) {
        User user = userService.findUserById(id);
        ModelAndView mav = new ModelAndView();
        mav = new ModelAndView("user-edit");
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute User user,
                                 BindingResult result,
                                 @PathVariable Long id,
                                 final RedirectAttributes redirectAttributes, @ModelAttribute("userLogin") String userLogin) {

        if (result.hasErrors())
            return new ModelAndView("user-edit");


        ModelAndView mav;
        String message = "";
        Boolean b1 = user.getLogin().equals(userLogin);
        if (user.getLogin().equals(userLogin)) {
            mav = new ModelAndView("redirect:/index.html");
            message = "User was successfully updated.";
            userService.updateUser(user);
        } else {
            mav = new ModelAndView("403");
        }
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("user-delete");
        modelAndView.addObject("user", userService.findUserById(id));
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public ModelAndView deleteUser(@PathVariable Long id,
                                   final RedirectAttributes redirectAttributes, @ModelAttribute("userLogin") String userLogin) {

        ModelAndView mav = new ModelAndView("redirect:/index.html");
        User user1 = userService.findUserById(id);
        if (user1.getLogin().equals(userLogin)) {
            User user = userService.removeUserById(id);
            String message = "The shop " + user.getLogin() + " was successfully deleted.";

            redirectAttributes.addFlashAttribute("message", message);
        } else {
            String message = "You can't delete other users)))0";
            redirectAttributes.addFlashAttribute("message", message);
        }
        return mav;
    }


    @RequestMapping(value = "users/{id}", method = RequestMethod.GET)
    public ModelAndView showUserProfile(@PathVariable Long id, @ModelAttribute String userLogin) {
        ModelAndView modelAndView = new ModelAndView("userProfile");
        modelAndView.addObject(userService.findUserById(id));
        modelAndView.addObject(userService.findUserById(id).getTasks());
        return modelAndView;
    }


}
