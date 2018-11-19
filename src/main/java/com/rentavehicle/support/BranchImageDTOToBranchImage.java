package com.rentavehicle.support;

import com.rentavehicle.model.Branch;
import com.rentavehicle.model.BranchImage;
import com.rentavehicle.service.BranchImageService;
import com.rentavehicle.service.BranchService;
import com.rentavehicle.web.dto.BranchImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BranchImageDTOToBranchImage implements Converter<BranchImageDTO, BranchImage> {

	@Autowired
	private BranchImageService branchImageService;

	@Autowired
	private BranchService branchService;

	@Override
	public BranchImage convert(BranchImageDTO dto) {

		BranchImage branchImage;

		if (dto.getId() == null) {
			Branch branch = branchService.findOne(dto.getBranchId());
			branchImage = new BranchImage();
			branchImage.setBranch(branch);
		} else {
			branchImage = branchImageService.findOne(dto.getId());
		}

		branchImage.setImageSource(dto.getImageSource());

		return branchImage;
	}

	public List<BranchImage> convert(List<BranchImageDTO> dtos) {
		List<BranchImage> branchImages = new ArrayList<>();

		for (BranchImageDTO dto : dtos) {
			branchImages.add(convert(dto));
		}

		return branchImages;
	}

}
