package com.rentavehicle.service;

import com.rentavehicle.model.DocImage;

import java.util.List;

public interface DocImageService {
	
	DocImage findOne(Long id);
	List<DocImage> findAll();
	void save(DocImage docImage);
	
}
