package com.Startup.BookStoreManagement.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.Startup.BookStoreManagement.dto.SiteUser;
import com.Startup.BookStoreManagement.repository.SiteUserRepository;

@Repository
public class SiteUserDao 
{
	@Autowired
	SiteUserRepository userRepository;
	public boolean existsByEmail(String email) 
	{
		
		return userRepository.existsByEmail(email);
	}
	
	public void saveUser(SiteUser portalUser)
	{
		userRepository.save(portalUser);
	}

	public SiteUser findUserById(int id) 
	{
		return userRepository.findById(id).orElse(null);
		
	}
	
	

	public boolean existsByMobile(Long mobile) 
	{
		
		return userRepository.existsByMobile(mobile);
	}
	
	public void deleteIfExists(String email)
	{
		SiteUser user=userRepository.findByEmail(email);
		if(user !=null)
		{
			userRepository.delete(user);
		}
	}

	public SiteUser findUserByMobile(long mobile) 
	{
		return userRepository.findByMobile(mobile);
	}

	public SiteUser findUserByEmail(String email) 
	{
		return userRepository.findByEmail(email);
	}
}
