package com.rentavehicle.support;

import com.rentavehicle.model.User;
import com.rentavehicle.service.UserRoleService;
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

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public User convert(UserDTO dto) {

        User user;

        if (dto.getId() == null) {
            user = new User();

        } else {
            user = userService.findOne(dto.getId());
        }

        user.setPassword(dto.getPassword());
        user.setUsername(dto.getUsername());
        user.setApproved(dto.isApproved());
        user.setBanned(dto.isBanned());
        user.setDeleted(dto.isDeleted());
        user.setDocImage(dto.getDocImage());


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
