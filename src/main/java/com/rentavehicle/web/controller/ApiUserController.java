package com.rentavehicle.web.controller;

import com.rentavehicle.model.User;
import com.rentavehicle.repository.UserRepository;
import com.rentavehicle.support.UserDTOToUser;
import com.rentavehicle.support.UserToUserDTO;
import com.rentavehicle.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {

//    @Autowired
//    private UserService userService;

    @Autowired
    UserDTOToUser toUser;

    @Autowired
    UserToUserDTO toDTO;

    @Autowired
    private UserRepository userRepository;

    //Putanja => localhost:8080/api/user/1

    //Za pristup ovoj metodi neophodno je da ulogovani korisnik ima ADMIN ulogu
    //Ukoliko nema, server ce vratiti gresku 403 Forbidden
    //Korisnik jeste autentifikovan, ali nije autorizovan da pristupi resursu

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<UserDTO> users() {
        return toDTO.convert(userRepository.findAll());
    }

    /**
     * Web service for getting a user by his ID
     *
     * @param id appUser ID
     * @return appUser
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> userById(@PathVariable Long id) {
        User appUser = userRepository.findOne(id);
        if (appUser == null) {
            return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<UserDTO>(toDTO.convert(appUser), HttpStatus.OK);
        }
    }

    /**
     * Method for deleting a user by his ID
     *
     * @param id
     * @return
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id) {
        User appUser = userRepository.findOne(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUsername = auth.getName();
        if (appUser == null) {
            return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);
        } else if (appUser.getUsername().equalsIgnoreCase(loggedUsername)) {
            throw new RuntimeException("You cannot delete your account");
        } else {
            userRepository.delete(appUser);
            return new ResponseEntity<UserDTO>(toDTO.convert(appUser), HttpStatus.OK);
        }

    }

    /**
     * Method for adding a appUser
     *
     * @param appUser
     * @return
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO appUser) {
        if (userRepository.findByUsername(appUser.getUsername()) != null) {
            throw new RuntimeException("Username already exist");
        }

        User user = toUser.convert(appUser);
        userRepository.save(user);

        return new ResponseEntity<UserDTO>(toDTO.convert(user), HttpStatus.CREATED);
    }

    /**
     * Method for editing an user details
     *
     * @param editedUser
     * @return modified appUser
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public UserDTO updateUser(@RequestBody UserDTO editedUser) {
        if (userRepository.findByUsername(editedUser.getUsername()) != null
                && userRepository.findByUsername(editedUser.getUsername()).getId() != editedUser.getId()) {
            throw new RuntimeException("Username already exist");
        }


        User converted = toUser.convert(editedUser);

        userRepository.save(converted);

        return toDTO.convert(converted);
    }

}
