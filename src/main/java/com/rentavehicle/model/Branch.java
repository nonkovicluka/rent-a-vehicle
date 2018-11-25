package com.rentavehicle.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "branchPickup", fetch = FetchType.LAZY)
    private List<Reservation> reservationsPickup = new ArrayList<Reservation>();

    @OneToMany(mappedBy = "branchDelivery", fetch = FetchType.LAZY)
    private List<Reservation> reservationsDelivery = new ArrayList<Reservation>();

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

    public void addReservationPickup(Reservation reservation) {
        this.reservationsPickup.add(reservation);

        if (!this.equals(reservation.getBranchPickup())) {
            reservation.setBranchPickup(this);
        }
    }

    public void addReservationDelivery(Reservation reservation) {
        this.reservationsDelivery.add(reservation);

        if (!this.equals(reservation.getBranchDelivery())) {
            reservation.setBranchDelivery(this);
        }
    }

    public List<Reservation> getReservationsPickup() {
        return reservationsPickup;
    }

    public void setReservationsPickup(List<Reservation> reservationsPickup) {
        this.reservationsPickup = reservationsPickup;
    }

    public List<Reservation> getReservationsDelivery() {
        return reservationsDelivery;
    }

    public void setReservationsDelivery(List<Reservation> reservationsDelivery) {
        this.reservationsDelivery = reservationsDelivery;
    }
}
