package com.sdp3.homestay.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.sdp3.homestay.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sdp3.homestay.services.UserService;
@Controller
public class HomeController {

	@Autowired
	UserService userServices;

    @GetMapping("/")
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }
    
    @GetMapping("/loginpage")
    public ModelAndView login(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
    	HttpSession session = request.getSession();
    	if(session.getAttribute("username")!=null) {
    		return new ModelAndView("redirect:/");
    	}
    	else {
    		ModelAndView mView = new ModelAndView();
    		mView.setViewName("loginpage");
    		return mView;
    	}
        
    }
    
    @GetMapping("/signuppage")
    public ModelAndView signup(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
    	HttpSession session = request.getSession();
    	if(session.getAttribute("username")!=null) {
    		return new ModelAndView("redirect:/");
    	}
    	else {
    		ModelAndView mView = new ModelAndView();
    		mView.setViewName("signup");
    		return mView;
    	}
    }

	@GetMapping("/profile")
	public ModelAndView profile(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("username");
		User users = userServices.getUserbyId(user);
		if(user==null) {
    		return new ModelAndView("redirect:/loginpage");
    	}
    	else {
    		ModelAndView mv = new ModelAndView();
    		mv.addObject("username",users.getUsername());
			mv.addObject("name",users.getName());
			mv.addObject("email",users.getEmail());
			mv.addObject("password",users.getPassword());
			mv.addObject("phone",users.getPhone());
    		mv.setViewName("profile");
    		return mv;
    	}
	}

	@GetMapping("/bookrooms")
	public ModelAndView rooms(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("rooms");
		return mv;
	}

	@GetMapping("/addroom")
	public ModelAndView listrooms(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
    	ModelAndView mView = new ModelAndView();
		if(session.getAttribute("username")==null) {
			return new ModelAndView("redirect:/loginpage");
    		
    	}
    	else {
    		mView.setViewName("listroom");
			return mView;
    	}
	}

	@GetMapping("/update")
	public ModelAndView update(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("username");
		if(user==null) {
    		return new ModelAndView("redirect:/loginpage");
    	}
    	else {
    		ModelAndView mv = new ModelAndView();
			User users = userServices.getUserbyId(user);
			mv.addObject("username",users.getUsername());
			mv.addObject("name",users.getName());
			mv.addObject("email",users.getEmail());
			mv.addObject("password",users.getPassword());
			mv.addObject("phone",users.getPhone());
    		mv.setViewName("updateprofile");
    		return mv;
    	}
	}
}
