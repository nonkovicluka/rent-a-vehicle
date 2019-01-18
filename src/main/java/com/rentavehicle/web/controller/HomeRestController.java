package com.rentavehicle.web.controller;

import com.rentavehicle.model.User;
import com.rentavehicle.model.UserRole;
import com.rentavehicle.repository.UserRepository;
import com.rentavehicle.service.UserRoleService;
import com.rentavehicle.support.UserToUserDTO;
import com.rentavehicle.web.dto.UserDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

/**
 * All web services in this controller will be available for all the users
 *
 * @author Hendi Santika
 */
@RestController
public class HomeRestController {

    @Autowired
    private UserRepository appUserRepository;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserToUserDTO toUserDTO;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    /**
     * This method is used for user registration. Note: user registration is not
     * require any authentication.
     *
     * @param appUser
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> createUser(@RequestBody User appUser) {
        if (appUserRepository.findByUsername(appUser.getUsername()) != null) {
            throw new RuntimeException("Username already exist");
        }
        UserRole userRole = userRoleService.findOne(3L); // ROLE_USER
        userRole.addUser(appUser);
        appUser.setUserRole(userRole);
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        appUserRepository.save(appUser);
        return new ResponseEntity<UserDTO>(toUserDTO.convert(appUser), HttpStatus.CREATED);
    }

    /**
     * This method will return the logged user.
     *
     * @param principal
     * @return Principal java security principal object
     */
    @RequestMapping("/user")
    public User user(Principal principal) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUsername = auth.getName();
        return appUserRepository.findByUsername(loggedUsername);
    }

    /**
     * @param username
     * @param password
     * @param response
     * @return JSON contains token and user after success authentication.
     * @throws IOException
     */
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> login(@RequestParam String username, @RequestParam String password,
                                                     HttpServletResponse response) throws IOException {
        String token = null;
        User appUser = appUserRepository.findByUsername(username);

        String userPass = appUser.getPassword();
        Map<String, Object> tokenMap = new HashMap<String, Object>();
        if (appUser != null && bCryptPasswordEncoder.matches(password, userPass)) {
            token = Jwts.builder().setSubject(username).claim("role", appUser.getUserRole().getAuthority()).setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
            tokenMap.put("token", token);

            UserDTO userDTO = toUserDTO.convert(appUser);
            tokenMap.put("user", userDTO);
            return new ResponseEntity<Map<String, Object>>(tokenMap, HttpStatus.OK);
        } else {
            tokenMap.put("token", null);
            return new ResponseEntity<Map<String, Object>>(tokenMap, HttpStatus.UNAUTHORIZED);
        }

    }
}
