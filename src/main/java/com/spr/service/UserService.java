package com.spr.service;

import com.spr.model.User;

import java.util.List;

/**
 * Created by Roman on 10.04.2014.
 */
public interface UserService {


    public User createUser(User user);

    public User updateUser(User user);

    public Iterable<User> findAllUsers();

    public void removeUser(User user);

    public User findUserById(Long id);

    public User removeUserById(Long id);

    public List<String> getUserLogins();

    public User getUserByLogin(String login);
}
