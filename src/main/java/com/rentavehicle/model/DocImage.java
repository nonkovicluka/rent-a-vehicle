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
public class DocImage {

	// attributes
	
	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column
	private String imageSource;

	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	
	// getters and setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageSource() {
		return imageSource;
	}

	public void setImageSource(String imageSource) {
		this.imageSource = imageSource;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;

		if (user != null && !user.getDocImages().contains(this)) {
			user.getDocImages().add(this);
		}
	}

}
