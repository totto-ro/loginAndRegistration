package com.codingdojo.controllers;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.models.User;
import com.codingdojo.services.UserService;
import com.codingdojo.validator.UserValidator;

@Controller
public class UserController {

    private final UserService userService;
    private final UserValidator userValidator; //import userValidator 
    
    public UserController (UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
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
    
    //Creates user (registration)
    @RequestMapping(value="/registration", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
        
    	//remove the type in the form:form inside the .jsp of the registrationPage to make validation work
    	userValidator.validate(user, result);
    	if( result.hasErrors() ) {
    		return "registrationPage.jsp";
    	}
    	User currentUser = userService.registerUser( user );
    	session.setAttribute( "userId" , currentUser.getId() );
    	return "redirect:/home";
    	
    }
    
    //login user
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam( value = "email") String email, 
    						@RequestParam( value = "password") String password, Model model, HttpSession session) {
        boolean authentication = userService.authenticateUser(email, password);
        
        if( authentication ) {
        	User currentUser = userService.findByEmail(email);
        	session.setAttribute( "userId", currentUser.getId() );
        	return "redirect:/home";
        }
        else {
        	model.addAttribute( "error", "Invalid Credentials. Please try again" );
        	return "loginPage.jsp";
        }
        
    }
    
    //Render home page
    @RequestMapping( value="/home", method=RequestMethod.GET )
    public String home(HttpSession session, Model model) {
        Long userId = ( Long ) session.getAttribute( "userId" );
        User currentUser = userService.findUserById( userId );
        model.addAttribute( "user", currentUser );
    	return "homePage.jsp";
    }
    
    
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
	
	
	
	
	

	
	
	
	
	
}
