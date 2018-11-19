package com.rentavehicle.repository;

import com.rentavehicle.model.DocImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocImageRepository extends JpaRepository<DocImage, Long> {

}
