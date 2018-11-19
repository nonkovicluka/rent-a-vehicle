package com.rentavehicle.repository;

import com.rentavehicle.model.BranchImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchImageRepository extends JpaRepository<BranchImage, Long> {

}
