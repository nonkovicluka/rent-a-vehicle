package com.rentavehicle.service;

import com.rentavehicle.model.Branch;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BranchService {

    Branch findOne(Long id);

    Page<Branch> findAll(int pageNum);

    List<Branch> findByAgencyId(Long agencyId);

    Page<Branch> findByAgencyId(int pageNum, Long agencyId);

    void save(Branch branch);
}
