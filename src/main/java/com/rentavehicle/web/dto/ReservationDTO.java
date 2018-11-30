package com.rentavehicle.web.dto;

public class ReservationDTO {

    private Long id;

    private String startDate;

    private String endDate;

    private double totalPrice;

    private Long userId;

    private String username;

    private Long vehicleId;

    private String vehicleName;

    private Long branchPickupId;

    private String branchPickupAddress;

    private Long branchDeliveryId;

    private String branchDeliveryAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
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

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getBranchPickupAddress() {
        return branchPickupAddress;
    }

    public void setBranchPickupAddress(String branchPickupAddress) {
        this.branchPickupAddress = branchPickupAddress;
    }

    public String getBranchDeliveryAddress() {
        return branchDeliveryAddress;
    }

    public void setBranchDeliveryAddress(String branchDeliveryAddress) {
        this.branchDeliveryAddress = branchDeliveryAddress;
    }
}
