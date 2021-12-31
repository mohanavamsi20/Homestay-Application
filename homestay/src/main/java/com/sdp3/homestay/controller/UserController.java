package com.sdp3.homestay.controller;
import com.sdp3.homestay.entity.User;
import com.sdp3.homestay.services.UserService;

import antlr.collections.List;

import java.io.IOException;
import java.security.Provider.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
		ModelAndView mv = new ModelAndView();
		mv.addObject("errorMsg","Username or password is Incorrect");
        mv.setViewName("loginpage");
		return mv;
    }

    @PostMapping("/signuppage")
    private ModelAndView signup(@Valid @ModelAttribute("usersSignup") User users,BindingResult result,RedirectAttributes attr){
		if(result.hasErrors()){
			attr.addFlashAttribute("errorMsg",result.getFieldError().getDefaultMessage());
			return new ModelAndView("redirect:/signuppage");
		}
		else if(userService.findUsername(users.getUsername())){
			attr.addFlashAttribute("errorMsg","username was already exist");
			return new ModelAndView("redirect:/signuppage");
		}
		else if(userService.findEmail(users.getEmail())){
			attr.addFlashAttribute("errorMsg","email was already exist");
			return new ModelAndView("redirect:/signuppage");
		}
		else if(userService.findphone(users.getPhone())){
			attr.addFlashAttribute("errorMsg","phone number was already in use");
			return new ModelAndView("redirect:/signuppage");
		}
		userService.saveUsers(users);
		attr.addFlashAttribute("successMsg","User Registration successful");
		return new ModelAndView("redirect:/loginpage");
    }
    
    @PostMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
		session.removeAttribute("username");
		session.invalidate();
		return new ModelAndView("redirect:/");
    }

	@PostMapping("/update")
	public ModelAndView update(@Valid @ModelAttribute("userUpdate") User users,BindingResult result,RedirectAttributes attr,HttpServletRequest request){
		User u1 = userService.getUserdetails(users.getUsername());
		if(result.hasErrors()){
			attr.addFlashAttribute("errorMsg",result.getFieldError().getDefaultMessage());
			return new ModelAndView("redirect:/update");
		}
		u1.setName(users.getName());
		u1.setEmail(users.getEmail());
		u1.setPassword(users.getPassword());
		u1.setPhone(users.getPhone());
		userService.saveUsers(u1);
		attr.addFlashAttribute("successMsg","User profile updated successfully");
		return new ModelAndView("redirect:/update");
	}

	
}
