package com.rentavehicle.service;

import com.rentavehicle.model.BranchImage;

import java.util.List;

public interface BranchImageService {
	
	BranchImage findOne(Long id);
	List<BranchImage> findAll();
	void save(BranchImage branchImage);
	
}
