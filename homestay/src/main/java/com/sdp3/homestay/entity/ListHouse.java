package com.sdp3.homestay.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class ListHouse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long houseid;
	
	String ownerusername;
	String housetype;
	String housename;
	String facilities;
	String description;
	String country;
	String state;
	String city;
	String address;
	String pincode;
	String localfacilities;
	String maximumstayduration;
	String rooms;
	long pricepernight;
	String housepic;
	String ownerpic;
}
