package com.spr.validation;


import com.spr.model.User;
import com.spr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created by Roman on 08.04.2014.
 */
@Component
public class AuthorizationValidator implements Validator {
    @Autowired
    UserRepository userRepository;


    public boolean supports(Class clazz) {
        return User.class.isAssignableFrom(clazz);
    }


    public void validate(Object target, Errors errors) {
        User user = (User) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "empty.field", "Enter your login!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "empty.field", "Enter your password!");

        List<User> list = (List<User>) userRepository.findAll();
        for (User user1 : list) {
            if (user.getLogin().equals(user1.getLogin()) == false) {
                errors.rejectValue("login", "user.login.alreadyExist", "You weren't found!");
            }
        }
    }

}
