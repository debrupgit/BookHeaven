package com.Startup.BookStoreManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Startup.BookStoreManagement.service.MyBookListService;

@Controller
public class MyBookListController 
{
	@Autowired
	MyBookListService bookListService;
	
	
	@RequestMapping("/deleteMyBook/{id}")
	public String deleteMyBook(@PathVariable("id") int  id)
	{
		bookListService.deleteBookById(id);
		return "redirect:/mybook";
	}
}
