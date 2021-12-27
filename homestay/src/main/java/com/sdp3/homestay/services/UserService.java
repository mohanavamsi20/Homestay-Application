package com.sdp3.homestay.services;

import com.sdp3.homestay.entity.User;
import com.sdp3.homestay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getallusers(){
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(usr -> users.add(usr));
        return users;
    }

    public List<User> getUserbyId(String username){
        List<User> users = userRepository.findByUsername(username);
        return users;
    }

    public boolean findUsername(String username){
        List<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            return false;
        }
        return true;
    }
    public boolean findEmail(String email){
        List<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()){
            return false;
        }
        return true;
    }
    public boolean findphone(String phone){
        List<User> user = userRepository.findByPhone(phone);
        if(user.isEmpty()){
            return false;
        }
        return true;
    }
    public boolean findUsernameAndPassword(String username,String password){
        List<User> user = userRepository.findByUsernameAndPassword(username,password);
        if(user.isEmpty()){
            return false;
        }
        return true;
    }
    
    public void saveUsers(User user) {
    	userRepository.save(user);
    }

    public void update(User user,String username){
        userRepository.save(user);
    }
}
