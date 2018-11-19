package com.rentavehicle.web.dto;

import java.time.LocalDateTime;

public class ReservationDTO {
	
	private Long id;
	
	private LocalDateTime startDate;
	
	private LocalDateTime endDate;
	
	private double totalPrice;
	
	private Long userId;
	
	private String username;
	
	private Long vehicleId;
	
	private Long branchPickupId;
	
	private Long branchDeliveryId;

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Long getBranchPickupId() {
		return branchPickupId;
	}

	public void setBranchPickupId(Long branchPickupId) {
		this.branchPickupId = branchPickupId;
	}

	public Long getBranchDeliveryId() {
		return branchDeliveryId;
	}

	public void setBranchDeliveryId(Long branchDeliveryId) {
		this.branchDeliveryId = branchDeliveryId;
	}
	
}
