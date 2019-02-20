package com.rentavehicle.service;

import com.rentavehicle.model.Agency;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AgencyService {

    Agency findOne(Long id);

    Page<Agency> findAll(int pageNum);

    void save(Agency agency);

    List<Agency> findByOwnerId(Long userId);

    Page<Agency> findByOwnerId(int pageNum, Long userId);

    void createImage(MultipartFile file,  String agencyName) throws IOException;
}
