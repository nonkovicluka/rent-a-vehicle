package com.rentavehicle.service.impl;

import com.rentavehicle.model.DocImage;
import com.rentavehicle.repository.DocImageRepository;
import com.rentavehicle.service.DocImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class JpaDocImageService implements DocImageService {

	@Autowired
	private DocImageRepository docImageRepository;

	@Override
	public DocImage findOne(Long id) {


		return docImageRepository.findOne(id);
	}

	@Override
	public List<DocImage> findAll() {
		// TODO Auto-generated method stub
		return docImageRepository.findAll();
	}

	@Override
	public void save(DocImage docImage) {
		// TODO Auto-generated method stub
		docImageRepository.save(docImage);
	}

}
