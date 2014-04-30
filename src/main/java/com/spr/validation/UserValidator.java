package com.spr.validation;

import com.spr.model.User;
import com.spr.repository.UserRepository;
import com.spr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * Created by Roman on 18.03.14.
 */

@Component
public class UserValidator implements Validator {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;


    public boolean supports(Class clazz) {
        return User.class.isAssignableFrom(clazz);
    }


    public void validate(Object target, Errors errors) {
        User user = (User) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "user.firstname.empty", "Name must not be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "secondname", "user.secondname.empty", "Surname must not be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "user.login.empty", "Login must not be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "user.password.empty", "Password !!!!!!");
        User user1 = userRepository.getUserByLogin(user.getLogin());
        if (user1 != null) {
            errors.rejectValue("login", "user.login.alreadyExist", "This login is exist");
        }

        if (user.getLogin().length() < 4) {
            errors.rejectValue("login", "user.login.length", "username length need to be more , than 4");
        }

    }
}
