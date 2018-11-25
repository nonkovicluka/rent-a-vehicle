package com.rentavehicle.model;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table
public class Reservation {

    // attributes

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime startDate;

    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime endDate;

    @Column
    private double totalPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "branch_pickup_id")
    private Branch branchPickup;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "branch_delivery_id")
    private Branch branchDelivery;


    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
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

        if (branchPickup != null && !branchPickup.getReservationsPickup().contains(this)) {
            branchPickup.getReservationsPickup().add(this);
        }
    }

    public Branch getBranchDelivery() {
        return branchDelivery;
    }

    public void setBranchDelivery(Branch branchDelivery) {
        this.branchDelivery = branchDelivery;

        if (branchDelivery != null && !branchDelivery.getReservationsDelivery().contains(this)) {
            branchDelivery.getReservationsDelivery().add(this);
        }
    }

}
