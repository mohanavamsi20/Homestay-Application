package com.sdp3.homestay.controller;
import com.sdp3.homestay.entity.User;
import com.sdp3.homestay.services.UserService;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
	public ModelAndView update(@RequestParam String username,@RequestParam String name,@RequestParam String email,
	@RequestParam String password,@RequestParam String phone,
	HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("username");
		User user1 = new User();
		user1.setName(name);
		user1.setEmail(email);
		user1.setPassword(password);
		user1.setPhone(phone);
		userService.update(user1, user);
		return new ModelAndView("redirect:/update");
	}
}
