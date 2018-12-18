package com.rentavehicle.support;

import com.rentavehicle.model.BranchImage;
import com.rentavehicle.web.dto.BranchImageDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BranchImageToBranchImageDTO implements Converter<BranchImage, BranchImageDTO> {

	@Override
	public BranchImageDTO convert(BranchImage branchImage) {

		if (branchImage == null) {
			return null;
		}

		BranchImageDTO dto = new BranchImageDTO();

		dto.setId(branchImage.getId());
		dto.setImageSource(branchImage.getImageSource());
		dto.setBranchId(branchImage.getBranch().getId());
		dto.setBranchAddress(branchImage.getBranch().getAddress());

		return dto;
	}

	public List<BranchImageDTO> convert(List<BranchImage> branchImages) {
		List<BranchImageDTO> ret = new ArrayList<>();

		for (BranchImage branchImage : branchImages) {
			ret.add(convert(branchImage));
		}

		return ret;
	}

}
