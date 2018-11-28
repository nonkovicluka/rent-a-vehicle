package com.rentavehicle.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table
public class VehicleType {

	// attributes

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column
	private String name;

	@OneToMany(targetEntity=Vehicle.class, fetch=FetchType.LAZY)
	private List<Vehicle> vehicles = new ArrayList<>();

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

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(ArrayList<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public void addVehicle(Vehicle vehicle) {
		this.vehicles.add(vehicle);

		if (!this.equals(vehicle.getVehicleType())) {
			vehicle.setVehicleType(this);
		}
	}

}
