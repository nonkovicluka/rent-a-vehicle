package com.rentavehicle.support;

import com.rentavehicle.model.Agency;
import com.rentavehicle.model.User;
import com.rentavehicle.service.AgencyService;
import com.rentavehicle.service.UserService;
import com.rentavehicle.web.dto.AgencyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AgencyDTOToAgency implements Converter<AgencyDTO, Agency> {

	@Autowired
	private AgencyService agencyService;

	@Autowired
	private UserService userService;

	@Override
	public Agency convert(AgencyDTO dto) {

		Agency agency;

		if (dto.getId() == null) {
			User user = userService.findOne(dto.getOwnerId());
			agency = new Agency();
			agency.setOwner(user);
		} else {
			agency = agencyService.findOne(dto.getId());
		}

		agency.setName(dto.getName());
		agency.setLogo(dto.getLogo());
		agency.setDescription(dto.getDescription());
		agency.setPhoneNumber(dto.getPhoneNumber());
		agency.setEmial(dto.getEmail());
		agency.setAverageScore(dto.getAverageScore());
		agency.setApproved(dto.isApproved());
		agency.setDeleted(dto.isDeleted());

		return agency;
	}

	public List<Agency> convert(List<AgencyDTO> dtos) {
		List<Agency> agencies = new ArrayList<>();

		for (AgencyDTO dto : dtos) {
			agencies.add(convert(dto));
		}

		return agencies;
	}

}
