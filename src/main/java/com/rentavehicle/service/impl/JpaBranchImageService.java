package com.rentavehicle.service.impl;

import com.rentavehicle.model.Branch;
import com.rentavehicle.model.BranchImage;
import com.rentavehicle.repository.BranchImageRepository;
import com.rentavehicle.service.BranchImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class JpaBranchImageService implements BranchImageService {

    private static String UPLOUD_ROOT = "/Users/lukanonkovic/RentAVehicle/src/main/resources/static/images/branch-images";
    private static String RELATIVE_ROOT = "images/branch-images";

    @Autowired
    private BranchImageRepository branchImageRepository;


    @Override
    public List<BranchImage> findByAgencyId(Long agencyId) {

        return branchImageRepository.findByAgencyId(agencyId);
    }

    @Override
    public void createImage(MultipartFile file, Branch branch, Long agencyId) throws IOException {

        if (!file.isEmpty()) {
            String path = UPLOUD_ROOT + "/" + agencyId + "/" + branch.getId();
            File theDir = new File(path);

            if (!theDir.exists()) {
                theDir.mkdirs();
            }
            Files.copy(file.getInputStream(), Paths.get(path, file.getOriginalFilename()));
            branchImageRepository.save(new BranchImage(RELATIVE_ROOT + "/" + agencyId + "/" + branch.getId() + "/" + file.getOriginalFilename(), branch));
        }
    }

    @Override
    public void deleteImage(String filename) throws IOException {
        BranchImage branchImage = branchImageRepository.findByImageSource(filename);
        branchImageRepository.delete(branchImage);
        Files.deleteIfExists(Paths.get(UPLOUD_ROOT, filename));
    }
}
