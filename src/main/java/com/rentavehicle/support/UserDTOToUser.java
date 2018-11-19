package com.rentavehicle.support;

import com.rentavehicle.model.User;
import com.rentavehicle.service.UserService;
import com.rentavehicle.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDTOToUser implements Converter<UserDTO, User> {

    @Autowired
    private UserService userService;

    @Override
    public User convert(UserDTO dto) {

        User user;

        if (dto.getId() == null) {
            user = new User();
        } else {
            user = userService.findOne(dto.getId());
        }

        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setProfileImage(dto.getProfileImage());
        user.setApproved(dto.isApproved());
        user.setBanned(dto.isBanned());
        user.setBirthDate(dto.getBirthDate());
        user.setDeleted(dto.isDeleted());

        return user;
    }

    public List<User> convert(List<UserDTO> dtos) {
        List<User> users = new ArrayList<>();

        for (UserDTO dto : dtos) {
            users.add(convert(dto));
        }

        return users;
    }

}
