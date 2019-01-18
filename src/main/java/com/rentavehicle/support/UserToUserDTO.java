package com.rentavehicle.support;

import com.rentavehicle.model.User;
import com.rentavehicle.web.dto.UserDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserToUserDTO implements Converter<User, UserDTO>{

	@Override
	public UserDTO convert(User user) {

		if(user == null) {
			return null;
		}

		UserDTO dto = new UserDTO();

		dto.setId(user.getId());
		dto.setUsername(user.getUsername());

		dto.setProfileImage(user.getProfileImage());
		dto.setBirthDate(user.getBirthDate());
		dto.setApproved(user.isApproved());
		dto.setBanned(user.isBanned());
		dto.setDeleted(user.isDeleted());
		dto.setUserRoleId(user.getUserRole().getId());
		dto.setUserRoleName(user.getUserRole().getName());

		return dto;
	}

	public List<UserDTO> convert(List<User> users) {
		List<UserDTO> ret = new ArrayList<>();

		for (User user : users) {
			ret.add(convert(user));
		}

		return ret;
	}

}
