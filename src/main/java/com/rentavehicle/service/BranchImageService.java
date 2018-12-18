package com.rentavehicle.service;

import com.rentavehicle.model.Branch;
import com.rentavehicle.model.BranchImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BranchImageService {

	List<BranchImage> findByAgencyId(Long agencyId);

	void createImage(MultipartFile file, Branch branch, Long agencyId) throws IOException;

	void deleteImage(String filename) throws IOException;
}
