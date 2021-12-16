package com.sdp3.homestay.controller;

import com.sdp3.homestay.entity.User;
import com.sdp3.homestay.services.UserService;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/loginpage")
    public ModelAndView login(@RequestParam String username, @RequestParam String password,HttpServletRequest request, HttpServletResponse response){
    	String uname = request.getParameter("username");
    	boolean check = userService.findUsernameAndPassword(username,password);
        if(check) {
        	HttpSession session=request.getSession();
            session.setAttribute("username", uname);
        	return new ModelAndView("redirect:/");
        }
        return new ModelAndView("redirect:/loginpage");
    }
    
    @PostMapping("/signuppage")
    private ModelAndView signup(@RequestParam String username,@RequestParam String name,@RequestParam String email,
    		@RequestParam String password,@RequestParam String cnfpassword,@RequestParam long phone) {
    	User users = new User();
    	users.setUsername(username);
    	users.setName(name);
    	users.setEmail(email);
    	users.setPassword(password);
    	users.setPhone(phone);
    	if(users.getPassword().equals(cnfpassword)) {
    		userService.saveUsers(users);
    		return new ModelAndView("redirect:/loginpage");
    	}
    	return new ModelAndView("redirect:/signuppage");
    	
    }
    
    @PostMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
		session.removeAttribute("username");
		session.invalidate();
		return new ModelAndView("redirect:/");
    }
}
