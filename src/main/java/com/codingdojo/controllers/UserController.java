package com.codingdojo.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.models.User;
import com.codingdojo.services.UserService;

@Controller
public class UserController {

    private final UserService userService;
    
    public UserController (UserService userService) {
        this.userService = userService;
    }
    
    //render register page
    @RequestMapping( value="/registration", method=RequestMethod.GET )
    public String registerForm(@ModelAttribute("user") User user) {
        return "registrationPage.jsp";
    }
    
    //render login page
    @RequestMapping( value="/login", method=RequestMethod.GET )
    public String login() {
        return "loginPage.jsp";
    }
    
    @RequestMapping(value="/registration", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
        // if result has errors, return the registration page (don't worry about validations just now)
        // else, save the user in the database, save the user id in session, and redirect them to the /home route
    	return "";
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
        // if the user is authenticated, save their user id in session
        // else, add error messages and return the login page
    	return "";
    }
    
    //Render home page
    @RequestMapping( value="/home", method=RequestMethod.GET )
    public String home(HttpSession session, Model model) {
        // get user from session, save them in the model and return the home page
    	return "homePage.jsp";
    }
    
    
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        // invalidate session
        // redirect to login page
    	return "";
    }
	
	
	
	
	

	
	
	
	
	
}
