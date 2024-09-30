package com.Startup.BookStoreManagement.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import com.Startup.BookStoreManagement.dao.SiteUserDao;
import com.Startup.BookStoreManagement.dto.SiteUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
@Service
public class SiteUserService 
{  
	@Autowired
	SiteUser siteUser;
	
	
	@Autowired
	SiteUserDao userDao;
	

	public String signUp(@Valid SiteUser siteUser, BindingResult result, ModelMap map) 
	{
		System.out.println(siteUser);
		if(siteUser.getDob()==null)
		{
			result.rejectValue("dob", "error.dob", " *select a date");
			System.out.println(" Error - age is not selected");
		}
		
		if (LocalDate.now().getYear() - siteUser.getDob().getYear() < 18)
		{
			result.rejectValue("dob", "error.dob", "* Age should be Greater Than 18");
			System.out.println(" Error - age is not greater than 18");
		}
			
		if (!siteUser.getPassword().equals(siteUser.getConfirm_password()))
		{
			result.rejectValue("confirm_password", "error.confirm_password",
					"* Password and Confirm Password Should be Matching");
		}
			
		if(userDao.existsByEmail(siteUser.getEmail())) 
		{
			result.rejectValue("email", "error.email", "* Email already exist");
		}
			
		if(userDao.existsByMobile(siteUser.getMobile()))
		{
			result.rejectValue("mobile", "error.mobile", "* mobile already exist");
		}

		if(result.hasErrors())
		{
			return "signup.html";
		}
		
		
		
		else
		{
		
			userDao.deleteIfExists(siteUser.getEmail());
			siteUser.setPassword(siteUser.getPassword());
			siteUser.setConfirm_password(siteUser.getConfirm_password());
			userDao.saveUser(siteUser);
			System.out.println("data is saved in db");
		
			
			return "home";
		}
	}
	
	public String login(String emph, String password, ModelMap map, HttpSession session) 
	{
		if(emph.equals("admin") && password.equals("admin")) {
			map.put("msg", "login success");
			siteUser.setRole("admin");
			session.setAttribute("siteUser", siteUser);
			return "home.html";
		}else {
		SiteUser siteUser=null;
		try
		{
			long mobile=Long.parseLong(emph);
			siteUser=userDao.findUserByMobile(mobile);
		}
		catch(NumberFormatException e)
		{
			String email=emph;
			siteUser=userDao.findUserByEmail(email);
		}
		if(siteUser==null)
		{
			map.put("msg", "invalid email or ph no");
			return "login.html";
		}
		
		else
		{
			if(password.equals(siteUser.getPassword()))
			{
				
					map.put("msg", "login success");
					session.setAttribute("siteUser", siteUser);
					
						return "home.html";
				
				
			}
			else
			{
				map.put("msg", "Invalid Password");
				return "login.html";
			}	
		}
	}
	}
		
	
}

