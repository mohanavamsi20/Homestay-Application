package com.sdp3.homestay.repository;

import org.springframework.data.repository.CrudRepository;

import com.sdp3.homestay.entity.ListHouse;

public interface HouseRepository extends CrudRepository<ListHouse,Long> {
	
}
