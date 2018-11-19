package com.rentavehicle.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class BranchImage {

	// attributes
	
	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column
	private String imageSource;

	@ManyToOne(fetch = FetchType.EAGER)
	private Branch branch;

	
	// getters and setters
	
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

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;

		if (branch != null && !branch.getBranchImages().contains(this)) {
			branch.getBranchImages().add(this);
		}
	}

}
