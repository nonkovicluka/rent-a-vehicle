package com.rentavehicle.service.impl;

import com.rentavehicle.model.VehicleImage;
import com.rentavehicle.repository.VehicleImageRepository;
import com.rentavehicle.service.VehicleImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class JpaVehicleImageService implements VehicleImageService {

    private static String UPLOUD_ROOT = "/Users/lukanonkovic/RentAVehicle/src/main/resources/static/images/vehicle-images";

    @Autowired
    private VehicleImageRepository vehicleImageRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    public Resource findOneImage(String filename) {

        return resourceLoader.getResource(UPLOUD_ROOT + "/" + filename);
    }

    public void createImage (MultipartFile file) throws IOException {

        if (!file.isEmpty()){
            Files.copy(file.getInputStream(), Paths.get(UPLOUD_ROOT, file.getOriginalFilename()));
            vehicleImageRepository.save(new VehicleImage(file.getOriginalFilename()));
        }
    }

    public void deleteImage (String filename) throws IOException {
        VehicleImage vehicleImage = vehicleImageRepository.findByName(filename);
        vehicleImageRepository.delete(vehicleImage);
        Files.deleteIfExists(Paths.get(UPLOUD_ROOT, filename));
    }
}
