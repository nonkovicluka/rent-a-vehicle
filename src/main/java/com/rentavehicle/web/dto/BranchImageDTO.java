package com.rentavehicle.web.dto;

public class BranchImageDTO {
	
	private Long id;
	
	private String imageSource;
	
	private Long branchId;
	
	private String branchAdress;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageSource() {
		return imageSource;
	}

	public void setImageSource(String imageSource) {
		this.imageSource = imageSource;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public String getBranchAdress() {
		return branchAdress;
	}

	public void setBranchAdress(String branchAdress) {
		this.branchAdress = branchAdress;
	}
	
	

}
