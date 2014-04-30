package com.spr.repository;

import com.spr.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Roman on 10.04.2014.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    @Transactional
    @Query(value = "select u from User u where u.login= ?1")
    User getUserByLogin(String login);

    @Transactional
    @Query("select u.login from User u")
    List<String> getUsersLogins();

}
