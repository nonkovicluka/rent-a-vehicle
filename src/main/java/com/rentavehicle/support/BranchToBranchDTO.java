package com.rentavehicle.support;

import com.rentavehicle.model.Branch;
import com.rentavehicle.web.dto.BranchDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BranchToBranchDTO implements Converter<Branch, BranchDTO> {

	@Override
	public BranchDTO convert(Branch branch) {

		if (branch == null) {
			return null;
		}

		BranchDTO dto = new BranchDTO();

		dto.setId(branch.getId());
		dto.setAddress(branch.getAddress());
		dto.setPhoneNumber(branch.getPhoneNumber());
		dto.setLatitude(branch.getLatitude());
		dto.setLongitude(branch.getLongitude());
		dto.setAgencyId(branch.getAgency().getId());
		dto.setAgencyName(branch.getAgency().getName());
		dto.setDeleted(branch.isDeleted());

		return dto;
	}

	public List<BranchDTO> convert(List<Branch> branches) {
		List<BranchDTO> ret = new ArrayList<>();

		for (Branch branch : branches) {
			ret.add(convert(branch));
		}

		return ret;
	}

}
