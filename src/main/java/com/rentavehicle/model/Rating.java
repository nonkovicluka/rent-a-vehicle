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
public class Rating {

	// attributes
	
	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column
	private int score;

	@Column
	private String comment;

	@ManyToOne(fetch = FetchType.EAGER)
	private Agency agency;

	
	// getters and setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;

		if (agency != null && !agency.getRatings().contains(this)) {
			agency.getRatings().add(this);
		}
	}
}
