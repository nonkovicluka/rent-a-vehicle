package com.rentavehicle.service.impl;

import com.rentavehicle.model.Agency;
import com.rentavehicle.repository.AgencyRepository;
import com.rentavehicle.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaAgencyService implements AgencyService {

    @Autowired
    private AgencyRepository agencyRepository;

    @Override
    public Agency findOne(Long id) {

        return agencyRepository.findOne(id);
    }

    @Override
    public Page<Agency> findAll(int pageNum) {

        return agencyRepository.findAll(new PageRequest(pageNum, 5));
    }


    @Override
    public void save(Agency agency) {

        agencyRepository.save(agency);
    }

    @Override
    public Page<Agency> findByOwnerId(int pageNum, Long userId) {

        return agencyRepository.findByOwnerId(userId, new PageRequest(pageNum, 5));
    }

    @Override
    public List<Agency> findByOwnerId(Long userId) {

        return agencyRepository.findByOwnerId(userId);
    }
}
