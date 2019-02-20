package com.rentavehicle.model;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class PriceList {

    // attributes

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate startDate;

    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Agency agency;

    @OneToMany(mappedBy = "priceList", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PriceListItem> priceListItems = new ArrayList<>();


    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<PriceListItem> getPriceListItems() {
        return priceListItems;
    }

    public void setPriceListItems(List<PriceListItem> priceListItems) {
        this.priceListItems = priceListItems;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;

        if (agency != null && !agency.getPriceLists().contains(this)) {
            agency.getPriceLists().add(this);
        }
    }

    public void addPriceListItem(PriceListItem priceListItem) {
        this.priceListItems.add(priceListItem);

        if (!this.equals(priceListItem.getPriceList())) {
            priceListItem.setPriceList(this);
        }
    }
}
