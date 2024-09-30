package com.Startup.BookStoreManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Startup.BookStoreManagement.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> 
{

}
