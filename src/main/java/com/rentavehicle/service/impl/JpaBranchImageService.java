package com.rentavehicle.service.impl;

import com.rentavehicle.model.BranchImage;
import com.rentavehicle.repository.BranchImageRepository;
import com.rentavehicle.service.BranchImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class JpaBranchImageService implements BranchImageService {

    @Autowired
    private BranchImageRepository branchImageRepository;

    @Override
    public BranchImage findOne(Long id) {

        return branchImageRepository.findOne(id);
    }

    @Override
    public List<BranchImage> findAll() {
        // TODO Auto-generated method stub
        return branchImageRepository.findAll();
    }

    @Override
    public void save(BranchImage branchImage) {
        // TODO Auto-generated method stub
        branchImageRepository.save(branchImage);
    }

}
