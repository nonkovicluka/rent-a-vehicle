package com.rentavehicle.service;

import com.rentavehicle.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {

    User findOne(Long id);

    User findByUsername(String username);

    List<User> findByApprovedFalseAndBannedFalse();

    List<User> findAll();

    void save(User user);

    void createImage(MultipartFile file, String username) throws IOException;


}
