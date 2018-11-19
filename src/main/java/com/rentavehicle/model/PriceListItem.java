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
public class PriceListItem {

	// attributes

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column
	private double pricePerHour;

	@ManyToOne(fetch = FetchType.EAGER)
	private Vehicle vehicle;

	@ManyToOne(fetch = FetchType.EAGER)
	private PriceList priceList;

	
	// getters and setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;

		if (vehicle != null && !vehicle.getPriceListItems().contains(this)) {
			vehicle.getPriceListItems().add(this);
		}
	}

	public PriceList getPriceList() {
		return priceList;
	}

	public void setPriceList(PriceList priceList) {
		this.priceList = priceList;

		if (priceList != null && !priceList.getPriceListItems().contains(this)) {
			priceList.getPriceListItems().add(this);
		}
	}

}
