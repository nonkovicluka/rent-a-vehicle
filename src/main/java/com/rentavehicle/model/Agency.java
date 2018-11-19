package com.rentavehicle.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Agency {

	// attributes

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;

	@Column
	private String logo;

	@Column
	private String description;

	@Column
	private String phoneNumber;

	@Column(nullable = false)
	private String email;

	@Column
	private double averageScore;

	@Column
	private boolean approved;

	@OneToMany(mappedBy = "agency", fetch = FetchType.LAZY)
	private List<Branch> branches = new ArrayList<Branch>();

	@OneToMany(mappedBy = "agency", fetch = FetchType.LAZY)
	private List<Vehicle> vehicles = new ArrayList<Vehicle>();

	@OneToMany(mappedBy = "agency", fetch = FetchType.LAZY)
	private List<Rating> ratings = new ArrayList<Rating>();

	@OneToMany(mappedBy = "agency", fetch = FetchType.LAZY)
	private List<PriceList> priceLists = new ArrayList<PriceList>();

	@ManyToOne(fetch = FetchType.EAGER)
	private User owner;

	@Column
	private boolean deleted;

	// getters and setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmial() {
		return email;
	}

	public void setEmial(String emial) {
		this.email = emial;
	}

	public double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public List<PriceList> getPriceLists() {
		return priceLists;
	}

	public void setPriceLists(List<PriceList> priceLists) {
		this.priceLists = priceLists;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;

		if (owner != null && !owner.getAgencies().contains(this)) {
			owner.getAgencies().add(this);
		}
	}

	public void addBranch(Branch branch) {
		this.branches.add(branch);

		if (!this.equals(branch.getAgency())) {
			branch.setAgency(this);
		}
	}

	public void addVehicle(Vehicle vehicle) {
		this.vehicles.add(vehicle);

		if (!this.equals(vehicle.getAgency())) {
			vehicle.setAgency(this);
		}
	}

	public void addRating(Rating rating) {
		this.ratings.add(rating);

		if (!this.equals(rating.getAgency())) {
			rating.setAgency(this);
		}
	}

	public void addPriceList(PriceList priceList) {
		this.priceLists.add(priceList);

		if (!this.equals(priceList.getAgency())) {
			priceList.setAgency(this);
		}
	}

}
