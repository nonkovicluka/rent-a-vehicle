package com.rentavehicle.service.impl;

import com.rentavehicle.model.UserRole;
import com.rentavehicle.repository.UserRoleRepository;
import com.rentavehicle.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class JpaUserRoleService implements UserRoleService {

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Override
	public UserRole findOne(Long id) {
		return userRoleRepository.findOne(id);
	}

	@Override
	public void save(UserRole userRole) {

		userRoleRepository.save(userRole);
	}

}
