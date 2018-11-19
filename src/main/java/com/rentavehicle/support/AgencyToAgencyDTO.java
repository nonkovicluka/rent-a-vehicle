package com.rentavehicle.support;

import com.rentavehicle.model.Agency;
import com.rentavehicle.web.dto.AgencyDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AgencyToAgencyDTO implements Converter<Agency, AgencyDTO> {

	@Override
	public AgencyDTO convert(Agency agency) {

		if (agency == null) {
			return null;
		}

		AgencyDTO dto = new AgencyDTO();

		dto.setId(agency.getId());
		dto.setName(agency.getName());
		dto.setLogo(agency.getLogo());
		dto.setDescription(agency.getDescription());
		dto.setPhoneNumber(agency.getPhoneNumber());
		dto.setEmail(agency.getEmial());
		dto.setAverageScore(agency.getAverageScore());
		dto.setApproved(agency.isApproved());
		dto.setOwnerId(agency.getOwner().getId());
		dto.setOwnerUsername(agency.getOwner().getUsername());
		dto.setDeleted(agency.isDeleted());

		return dto;
	}

	public List<AgencyDTO> convert(List<Agency> agencies) {
		List<AgencyDTO> ret = new ArrayList<>();

		for (Agency agency : agencies) {
			ret.add(convert(agency));
		}

		return ret;
	}
}
