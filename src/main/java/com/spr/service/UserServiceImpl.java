package com.spr.service;

import com.spr.model.User;
import com.spr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Roman on 10.04.2014.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User createUser(User user) {
        User createdUser = user;
        return userRepository.save(createdUser);
    }

    @Override
    @Transactional
    public User updateUser(User user) {

        User updatedUser = userRepository.findOne(user.getId());
        updatedUser.setId(user.getId());
        updatedUser.setFirstname(user.getFirstname());
        updatedUser.setSecondname(user.getSecondname());
        updatedUser.setLogin(user.getLogin());
        updatedUser.setPassword(user.getPassword());

        return updatedUser;
    }

    @Override
    @Transactional
    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void removeUser(User user) {
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public User findUserById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    @Transactional
    public User removeUserById(Long id) {
        User deletedUser = userRepository.findOne(id);
        userRepository.delete(id);
        return deletedUser;
    }

    @Override
    public List<String> getUserLogins() {
        return userRepository.getUsersLogins();
    }

    @Override
     public User getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }
}
