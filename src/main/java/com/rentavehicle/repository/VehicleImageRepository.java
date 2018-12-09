package com.rentavehicle.repository;

import com.rentavehicle.model.VehicleImage;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleImageRepository extends PagingAndSortingRepository<VehicleImage, Long> {

    VehicleImage findByName(String name);

}
