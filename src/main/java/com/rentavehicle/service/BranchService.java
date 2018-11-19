package com.rentavehicle.service;

import com.rentavehicle.model.Branch;
import org.springframework.data.domain.Page;

public interface BranchService {

	Branch findOne(Long id);

	Page<Branch> findAll(int pageNum);

	void save(Branch branch);

	Page<Branch> findByAgencyId(int pageNum, Long agencyId);

}
