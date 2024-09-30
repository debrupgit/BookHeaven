package com.Startup.BookStoreManagement.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.Startup.BookStoreManagement.dao.SiteUserDao;
import com.Startup.BookStoreManagement.dto.SiteUser;

import jakarta.servlet.http.HttpSession;

public class CustomerService 
{
	@Autowired
	SiteUserDao userDao;
	@Autowired
	SiteUser siteuser;
	
	public String createCustomer(String email, String password, HttpSession session)
	{
		if (userDao.existsByEmail(email)) {
			session.setAttribute("failure","Account Already Exists");
			return "redirect:/";
		}
		else {
			siteuser.setEmail(email);
			siteuser.setPassword(password);
			siteuser.setRole("admin");
	
		userDao.saveUser(siteuser);
		session.setAttribute("success", "Customer Account Created Success");
		return "redirect:/";
		}
		
	}
}
