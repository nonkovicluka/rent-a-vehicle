package com.rentavehicle.service;

import com.rentavehicle.model.UserRole;

import java.util.List;

public interface UserRoleService {

    UserRole findOne(Long id);

    void save(UserRole userRole);

}
