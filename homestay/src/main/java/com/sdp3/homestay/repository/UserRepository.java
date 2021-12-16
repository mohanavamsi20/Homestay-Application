package com.sdp3.homestay.repository;

import com.sdp3.homestay.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {
    List<User> findByUsernameAndPassword(String username,String password);
}
