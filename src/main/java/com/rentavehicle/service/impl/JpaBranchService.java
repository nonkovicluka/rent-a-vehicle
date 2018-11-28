package com.rentavehicle.service.impl;

import com.rentavehicle.model.Branch;
import com.rentavehicle.repository.BranchRepository;
import com.rentavehicle.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class JpaBranchService implements BranchService {

	@Autowired
	private BranchRepository branchRepository;

	@Override
	public Branch findOne(Long id) {

		return branchRepository.findOne(id);
	}

	@Override
	public Page<Branch> findAll(int pageNum) {
		// TODO Auto-generated method stub
		return branchRepository.findAll(new PageRequest(pageNum, 3));
	}

	@Override
	public void save(Branch branch) {
		// TODO Auto-generated method stub
		branchRepository.save(branch);
	}

	@Override
	public Page<Branch> findByAgencyId(int pageNum, Long agencyId) {

		return branchRepository.findByAgencyId(agencyId, new PageRequest(pageNum, 5));
	}

}
