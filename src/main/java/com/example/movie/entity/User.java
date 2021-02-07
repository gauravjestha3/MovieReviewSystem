package com.example.movie.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "user")
public class User {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;

	@Column(name = "user_type")
	private String userType;

	

	@Column(name = "user_name")
	private String userName;
	
	
	@Column(name = "review_count")
	private int reviewCount;


	public Long getID() {
		return ID;
	}


	public void setID(Long iD) {
		ID = iD;
	}


	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public int getReviewCount() {
		return reviewCount;
	}


	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}



	
	
	
	
}
