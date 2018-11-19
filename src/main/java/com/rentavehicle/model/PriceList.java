package com.rentavehicle.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class PriceList {
	
	// attributes

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column
	private LocalDateTime startDate;

	@Column
	private LocalDateTime endDate;

	@ManyToOne(fetch = FetchType.EAGER)
	private Agency agency;

	@OneToMany(targetEntity=PriceListItem.class, fetch=FetchType.LAZY)
	private List<PriceListItem> priceListItems = new ArrayList<>();

	
	// getters and setters
	
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
