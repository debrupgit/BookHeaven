package com.Startup.BookStoreManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Startup.BookStoreManagement.entity.Book;
import com.Startup.BookStoreManagement.entity.MyBookList;
import com.Startup.BookStoreManagement.repository.MyBookRepository;

@Service
public class MyBookListService 
{
	@Autowired
	private MyBookRepository mybook;
	
	public void saveMyBooks(MyBookList book)
	{
		mybook.save(book);
	}
	
	public List<MyBookList> getMyBooks()
	{
		return mybook.findAll();
	}
	
	public void deleteBookById(int id)
	{
		mybook.deleteById(id);
	}
	
	
	
}
