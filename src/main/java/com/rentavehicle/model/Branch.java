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
public class Branch {

	// attributes

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column(nullable = false)
	private String address;

	@Column
	private String phoneNumber;

	@Column
	private double latitude;

	@Column
	private double longitude;

	@OneToMany(targetEntity=Reservation.class, fetch=FetchType.LAZY)
	private List<Reservation> reservations = new ArrayList<Reservation>();

	@OneToMany(mappedBy = "branch", fetch = FetchType.LAZY)
	private List<BranchImage> branchImages = new ArrayList<BranchImage>();

	@ManyToOne(fetch = FetchType.EAGER)
	private Agency agency;

	@Column
	private boolean deleted;

	// getters and setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public List<BranchImage> getBranchImages() {
		return branchImages;
	}

	public void setBranchImages(List<BranchImage> branchImages) {
		this.branchImages = branchImages;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;

		if (agency != null && !agency.getBranches().contains(this)) {
			agency.getBranches().add(this);
		}
	}

	public void addBranchImage(BranchImage branchImage) {
		this.branchImages.add(branchImage);

		if (!this.equals(branchImage.getBranch())) {
			branchImage.setBranch(this);
		}
	}

	public void addReservation(Reservation reservation) {
		this.reservations.add(reservation);

//		if (!this.equals(reservation.getBranchDelivery())) {
//			reservation.setBranchDelivery(this);
//		}
		if (!this.equals(reservation.getBranchPickup())) {
			reservation.setBranchPickup(this);
		}
	}

}
