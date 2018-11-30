package com.rentavehicle.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table
public class Vehicle {

    // attributes

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private boolean available;

    @Column
    private String description;

    @Column
    private String specification;

    @ManyToOne(fetch = FetchType.EAGER)
    private VehicleType vehicleType;

    @ManyToOne(fetch = FetchType.EAGER)
    private Agency agency;

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<VehicleImage> vehicleImages = new ArrayList<VehicleImage>();

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Reservation> reservations = new ArrayList<Reservation>();

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PriceListItem> priceListItems = new ArrayList<PriceListItem>();

    @Column
    private boolean deleted;

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;

        if (vehicleType != null && !vehicleType.getVehicles().contains(this)) {
            vehicleType.getVehicles().add(this);
        }
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;

        if (agency != null && !agency.getVehicles().contains(this)) {
            agency.getVehicles().add(this);
        }
    }

    public List<VehicleImage> getVehicleImages() {
        return vehicleImages;
    }

    public void setVehicleImages(List<VehicleImage> vehicleImages) {
        this.vehicleImages = vehicleImages;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public List<PriceListItem> getPriceListItems() {
        return priceListItems;
    }

    public void setPriceListItems(List<PriceListItem> priceListItems) {
        this.priceListItems = priceListItems;
    }

    public void addVehicleImage(VehicleImage vehicleImage) {
        this.vehicleImages.add(vehicleImage);

        if (!this.equals(vehicleImage.getVehicle())) {
            vehicleImage.setVehicle(this);
        }
    }

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);

        if (!this.equals(reservation.getVehicle())) {
            reservation.setVehicle(this);
        }
    }

    public void addPriceListItem(PriceListItem priceListItem) {
        this.priceListItems.add(priceListItem);

        if (!this.equals(priceListItem.getVehicle())) {
            priceListItem.setVehicle(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
