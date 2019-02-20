package com.rentavehicle.web.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentavehicle.model.User;
import com.rentavehicle.model.UserRole;
import com.rentavehicle.service.UserRoleService;
import com.rentavehicle.service.UserService;
import com.rentavehicle.support.UserDTOToUser;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * All web services in this controller will be available for all the users
 *
 * @author Hendi Santika
 */
@RestController
public class HomeRestController {

    private static String RELATIVE_ROOT = "images/user-doc";

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserToUserDTO toUserDTO;

    @Autowired
    private UserDTOToUser toUser;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> add(@RequestParam(required = false) MultipartFile doc, @RequestParam String userJson, @RequestParam String userRole) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        UserDTO userDTO = mapper.readValue(userJson, UserDTO.class);
        User user = toUser.convert(userDTO);

        if (userService.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }


        if (userRole.equals("User")) {
            UserRole roleUser = userRoleService.findOne(3L); // ROLE_USER
            roleUser.addUser(user);
            user.setUserRole(roleUser);

        }
        if (userRole.equals("Manager")) {
            UserRole roleManager = userRoleService.findOne(2L); // ROLE_MANAGER
            roleManager.addUser(user);
            user.setUserRole(roleManager);
        } else {
            UserRole roleUser = userRoleService.findOne(3L); // ROLE_USER
            roleUser.addUser(user);
            user.setUserRole(roleUser);
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));


        if (doc != null) {
            user.setDocImage(RELATIVE_ROOT + "/" + user.getUsername() + "/" + doc.getOriginalFilename());
            userService.createImage(doc, user.getUsername());
        }

        try {
            userService.save(user);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(toUserDTO.convert(user), HttpStatus.CREATED);
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
        return userService.findByUsername(loggedUsername);
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
        User appUser = userService.findByUsername(username);

        String userPass = appUser.getPassword();
        Map<String, Object> tokenMap = new HashMap<String, Object>();
        if (appUser != null && bCryptPasswordEncoder.matches(password, userPass)) {
            Date issuedDate = new Date();
            Date expDate = new Date(issuedDate.getTime() + 600000); // 10 minutes

            token = Jwts.builder().setSubject(username).claim("role", appUser.getUserRole().getAuthority()).setIssuedAt(issuedDate)
//                    .setExpiration(expDate)
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
