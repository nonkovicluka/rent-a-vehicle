package com.rentavehicle.web.dto;

import com.rentavehicle.model.PriceListItem;
import com.rentavehicle.model.Vehicle;

public class VehiclePriceListItem {

    private Vehicle vehicle;
    private PriceListItem priceListItem;

    public VehiclePriceListItem() {
    }

    public VehiclePriceListItem(Vehicle vehicle, PriceListItem priceListItem) {
        this.vehicle = vehicle;
        this.priceListItem = priceListItem;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public PriceListItem getPriceListItem() {
        return priceListItem;
    }

    public void setPriceListItem(PriceListItem priceListItem) {
        this.priceListItem = priceListItem;
    }
}
