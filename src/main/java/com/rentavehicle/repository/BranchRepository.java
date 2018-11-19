package com.rentavehicle.repository;

import com.rentavehicle.model.Branch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

	Page<Branch> findByAgencyId(Long agencyId, Pageable pageRequest);

}
