package com.Startup.BookStoreManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Startup.BookStoreManagement.dto.SiteUser;


public interface SiteUserRepository extends JpaRepository<SiteUser, Integer>
{
	boolean existsByEmail(String email);

	SiteUser findByEmail(String email);

	boolean existsByMobile(long mobile);

	SiteUser findByMobile(long mobile);
}
