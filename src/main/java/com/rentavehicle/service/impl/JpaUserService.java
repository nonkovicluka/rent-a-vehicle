package com.rentavehicle.service.impl;

import com.rentavehicle.model.User;
import com.rentavehicle.repository.UserRepository;
import com.rentavehicle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class JpaUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findOne(Long id) {

        return userRepository.findOne(id);
    }

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findByUsername(username);
        return u;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    @Override
    public void save(User user) {

        userRepository.save(user);
    }

}
