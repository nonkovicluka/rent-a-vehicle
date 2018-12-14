package com.rentavehicle.service;

import com.rentavehicle.model.Vehicle;
import com.rentavehicle.model.VehicleImage;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface VehicleImageService {

//    List<VehicleImage> findByVehicleId(Long vehicleId);

    List<VehicleImage> findByAgencyId(Long agencyId);

    void createImage(MultipartFile file, Vehicle vehicle, Long agencyId) throws IOException;

    void deleteImage(String filename) throws IOException;


}
