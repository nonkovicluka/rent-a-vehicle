package com.rentavehicle.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface VehicleImageService {

    Resource findOneImage(String filename);

    void createImage(MultipartFile file) throws IOException;

    void deleteImage(String filename) throws IOException;


}
