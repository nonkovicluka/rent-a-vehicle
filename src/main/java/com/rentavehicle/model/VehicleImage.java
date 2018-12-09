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
public class VehicleImage {

	// attributes

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column
	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	private Vehicle vehicle;


	public VehicleImage() {
	}

	public VehicleImage(String name) {
		this.name = name;
	}

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

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;

		if (vehicle != null && !vehicle.getVehicleImages().contains(this)) {
			vehicle.getVehicleImages().add(this);
		}
	}

}
