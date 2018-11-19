package com.rentavehicle.repository;

import com.rentavehicle.model.Agency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {

    Page<Agency> findByOwnerId(Long userId, Pageable pageRequest);

}
