package com.rentavehicle.service;

import com.rentavehicle.model.Agency;
import com.rentavehicle.model.Branch;
import org.springframework.data.domain.Page;

public interface AgencyService {

	Agency findOne(Long id);

	Page<Agency> findAll(int pageNum);

	void save(Agency agency);

	Page<Agency> findByOwnerId(int pageNum, Long userId);

}
