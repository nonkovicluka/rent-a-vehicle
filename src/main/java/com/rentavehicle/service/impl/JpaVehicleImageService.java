package com.rentavehicle.service.impl;

import com.rentavehicle.model.Vehicle;
import com.rentavehicle.model.VehicleImage;
import com.rentavehicle.repository.VehicleImageRepository;
import com.rentavehicle.service.VehicleImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class JpaVehicleImageService implements VehicleImageService {

    private static Path currentRelativePath = Paths.get("");
    private static String currentPath = currentRelativePath.toAbsolutePath().toString();

    private static String UPLOUD_ROOT = currentPath + "/target/classes/static/images/vehicle-images";
    private static String RELATIVE_ROOT = "images/vehicle-images";


    @Autowired
    private VehicleImageRepository vehicleImageRepository;


    @Override
    public List<VehicleImage> findByAgencyId(Long agencyId) {

        return vehicleImageRepository.findByAgencyId(agencyId);
    }

    @Override
    public List<VehicleImage> top3VehicleImages() {

        return vehicleImageRepository.top3VehicleImages();
    }

    //    @Override
//    public List<VehicleImage> findByVehicleId(Long vehicleId) {
//
//        return vehicleImageRepository.findByVehicleId(vehicleId);
//    }

    public void createImage(MultipartFile file, Vehicle vehicle, Long agencyId) throws IOException {

        if (!file.isEmpty()) {
            String path = UPLOUD_ROOT + "/" + agencyId + "/" + vehicle.getId();
            File theDir = new File(path);

            if (!theDir.exists()) {
                theDir.mkdirs();
            }
            Files.copy(file.getInputStream(), Paths.get(path, file.getOriginalFilename()));
            vehicleImageRepository.save(new VehicleImage(RELATIVE_ROOT + "/" + agencyId + "/" + vehicle.getId() + "/" + file.getOriginalFilename(), vehicle));
        }
    }

    public void deleteImage(String filename) throws IOException {
        VehicleImage vehicleImage = vehicleImageRepository.findByName(filename);
        vehicleImageRepository.delete(vehicleImage);
        Files.deleteIfExists(Paths.get(UPLOUD_ROOT, filename));
    }
}
