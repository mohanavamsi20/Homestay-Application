package com.sdp3.homestay.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

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
}
