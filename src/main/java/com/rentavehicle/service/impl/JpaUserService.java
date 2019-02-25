package com.rentavehicle.service.impl;

import com.rentavehicle.model.User;
import com.rentavehicle.repository.UserRepository;
import com.rentavehicle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class JpaUserService implements UserService {

    private static Path currentRelativePath = Paths.get("");
    private static String currentPath = currentRelativePath.toAbsolutePath().toString();

    private static String UPLOUD_ROOT = currentPath + "/target/classes/static/images/user-doc";

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

    @Override
    public void createImage(MultipartFile file, String username) throws IOException {
        if (!file.isEmpty()) {
            String path = UPLOUD_ROOT + "/" + username;
            File theDir = new File(path);

            if (!theDir.exists()) {
                theDir.mkdirs();
            }
            Files.copy(file.getInputStream(), Paths.get(path, file.getOriginalFilename()));
        }
    }

    @Override
    public List<User> findByApprovedFalseAndBannedFalse() {
        return userRepository.findByApprovedFalseAndBannedFalse();
    }
}
