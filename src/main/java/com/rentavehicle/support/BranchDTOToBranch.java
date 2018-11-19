package com.rentavehicle.support;

import com.rentavehicle.model.Agency;
import com.rentavehicle.model.Branch;
import com.rentavehicle.service.AgencyService;
import com.rentavehicle.service.BranchService;
import com.rentavehicle.web.dto.BranchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BranchDTOToBranch implements Converter<BranchDTO, Branch> {

	@Autowired
	private BranchService branchService;

	@Autowired
	private AgencyService agencyService;

	@Override
	public Branch convert(BranchDTO dto) {

		Branch branch;

		if (dto.getId() == null) {
			Agency agency = agencyService.findOne(dto.getAgencyId());
			branch = new Branch();
			branch.setAgency(agency);
		} else {
			branch = branchService.findOne(dto.getId());
		}

		branch.setAddress(dto.getAddress());
		branch.setPhoneNumber(dto.getPhoneNumber());
		branch.setLatitude(dto.getLatitude());
		branch.setLongitude(dto.getLongitude());
		branch.setDeleted(dto.isDeleted());

		return branch;
	}

	public List<Branch> convert(List<BranchDTO> dtos) {
		List<Branch> branches = new ArrayList<>();

		for (BranchDTO dto : dtos) {
			branches.add(convert(dto));
		}

		return branches;
	}

}
