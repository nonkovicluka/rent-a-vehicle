package com.rentavehicle.service;

import com.rentavehicle.model.User;

import java.util.List;

public interface UserService {

    User findOne(Long id);

    User findByUsername(String username);

    List<User> findAll();

    void save(User user);


}
