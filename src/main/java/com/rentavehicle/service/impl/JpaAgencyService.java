package com.rentavehicle.service.impl;

import com.rentavehicle.model.Agency;
import com.rentavehicle.repository.AgencyRepository;
import com.rentavehicle.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class JpaAgencyService implements AgencyService {

    private static Path currentRelativePath = Paths.get("");
    private static String currentPath = currentRelativePath.toAbsolutePath().toString();

    private static String UPLOUD_ROOT = currentPath + "/target/classes/static/images/agency-logo";


    @Autowired
    private AgencyRepository agencyRepository;

    @Override
    public Agency findOne(Long id) {

        return agencyRepository.findOne(id);
    }

    @Override
    public Page<Agency> findAll(int pageNum) {

        return agencyRepository.findAll(new PageRequest(pageNum, 4));
    }


    @Override
    public void save(Agency agency) {

        agencyRepository.save(agency);
    }

    @Override
    public Page<Agency> findByOwnerId(int pageNum, Long userId) {

        return agencyRepository.findByOwnerId(userId, new PageRequest(pageNum, 3));
    }

    @Override
    public List<Agency> findByOwnerId(Long userId) {

        return agencyRepository.findByOwnerId(userId);
    }

    @Override
    public void createImage(MultipartFile file, String agencyName) throws IOException {
        if (!file.isEmpty()) {
            String path = UPLOUD_ROOT + "/" + agencyName;
            File theDir = new File(path);

            if (!theDir.exists()) {
                theDir.mkdirs();
            }
            Files.copy(file.getInputStream(), Paths.get(path, file.getOriginalFilename()));
        }
    }
}
