package com.rentavehicle.support;

import com.rentavehicle.model.User;
import com.rentavehicle.model.UserRole;
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
            UserRole userRole = userRoleService.findOne(dto.getUserRoleId());
            user = new User();
            user.setUserRole(userRole);
        } else {
            user = userService.findOne(dto.getId());
        }

        user.setUsername(dto.getUsername());
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
