package com.sdp3.homestay.entity;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long userid;

    @Column(unique = true, updatable = false)
    @Size(min = 4,max = 15,message = "username size should be min 4 and max 15 characters")
    String username;

    String name;

    @Column(unique = true)
    @Email(message = "Enter the valid Email")
    String email;

    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$" ,message = "Password should be Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character")
    String password;

    @Column(nullable = false, unique = true)
    @Pattern(regexp="(^$|[0-9]{10})",message = "Enter valid phone number")
    String phone;

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
