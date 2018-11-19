package com.rentavehicle.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Reservation {

	// attributes
	
	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column
	private LocalDateTime startDate;

	@Column
	private LocalDateTime endDate;

	@Column
	private double totalPrice;

	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	private Vehicle vehicle;

	@ManyToOne(fetch = FetchType.EAGER)
	private Branch branchPickup;

	@ManyToOne(fetch = FetchType.EAGER)
	private Branch branchDelivery;

	
	// getters and setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;

		if (user != null && !user.getReservations().contains(this)) {
			user.getReservations().add(this);
		}
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;

		if (vehicle != null && !vehicle.getReservations().contains(this)) {
			vehicle.getReservations().add(this);
		}
	}

	public Branch getBranchPickup() {
		return branchPickup;
	}

	public void setBranchPickup(Branch branchPickup) {
		this.branchPickup = branchPickup;

		if (branchPickup != null && !branchPickup.getReservations().contains(this)) {
			branchPickup.getReservations().add(this);
		}
	}

	public Branch getBranchDelivery() {
		return branchDelivery;
	}

	public void setBranchDelivery(Branch branchDelivery) {
		this.branchDelivery = branchDelivery;

		if (branchDelivery != null && !branchDelivery.getReservations().contains(this)) {
			branchDelivery.getReservations().add(this);
		}
	}

}
