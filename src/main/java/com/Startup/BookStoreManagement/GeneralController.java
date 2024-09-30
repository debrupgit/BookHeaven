package com.Startup.BookStoreManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Startup.BookStoreManagement.dto.SiteUser;
import com.Startup.BookStoreManagement.service.SiteUserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class GeneralController 
{
	@Autowired
	SiteUser siteUser;
	@Autowired
	SiteUserService userService;
	
	
	@GetMapping("/sign-up")
	public String loadSignup(ModelMap map)
	{
		map.put("siteUser", siteUser);
		return "signup";
	}
	
	@GetMapping("/log-in")
	public String loadLogin()
	{
		return "login";
	}
	
	@PostMapping("/signup")
	public String signup(@Valid SiteUser siteUser, BindingResult result,ModelMap map)
	{
		return userService.signUp(siteUser, result,map);
			
	}
	@PostMapping("/login")
	public String login(@RequestParam("username") String emph, @RequestParam String password,ModelMap map,HttpSession session)
	{
		return userService.login(emph, password, map,session);
	}
	@GetMapping("/logout")
	public String logout(HttpSession session)
	{
		session.removeAttribute("siteUser");
		session.setAttribute("success", "Logout Success");
		return "redirect:/";
	}
	
}
