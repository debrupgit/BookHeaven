package com.Startup.BookStoreManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Startup.BookStoreManagement.dao.SiteUserDao;
import com.Startup.BookStoreManagement.dto.SiteUser;
import jakarta.servlet.http.HttpSession;
@Service
public class AdminService 
{
	@Autowired
	SiteUser siteuser;
	@Autowired
	SiteUserDao userDao;
	
	public String createAdmin(String email, String password, HttpSession session)
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
		session.setAttribute("success", "Admin Account Created Success");
		return "redirect:/";
		}
		
	}
}
