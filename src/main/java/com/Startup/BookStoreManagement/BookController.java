package com.Startup.BookStoreManagement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Startup.BookStoreManagement.entity.Book;
import com.Startup.BookStoreManagement.entity.MyBookList;
import com.Startup.BookStoreManagement.service.BookService;
import com.Startup.BookStoreManagement.service.MyBookListService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class BookController 
{
	@Autowired
	private BookService service;
	@Autowired
	private MyBookListService bookListService;
	
	@GetMapping("/")
	public String home() 
	{
		return "home";
	}
	
	@GetMapping("/book-register")
	public String bookRegister() {
		return "bookRegister";
	}
	
	@GetMapping("/available-books")
	public ModelAndView getAllBook(Model model) 
	{
		List<Book> blist=service.getAllBooks();
		return new ModelAndView("bookList","book",blist);
		
	}
	
	@PostMapping("/save")
	public String addBook(Book b) {
		//TODO: process POST request
		service.save(b);
		return "redirect:/available-books";
	}
	
	@GetMapping("/mybook")
	public String getMyBooks(Model model) 
	{
		List<MyBookList>blist=bookListService.getMyBooks();
		model.addAttribute("book", blist);
		return "myBooks";
	}
	
	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id") int id)
	{
		Book b=service.getBookById(id);
		MyBookList mb=new MyBookList(b.getId(),b.getBname(),b.getAuthor(),b.getPrice());
		bookListService.saveMyBooks(mb);
		return "redirect:/mybook";
	}

	@GetMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id, Model model) 
	{
		Book b1=service.getBookById(id);
		model.addAttribute("book", b1);
		return "bookedit";
	}
	
	@RequestMapping("/deleteBook/{id}")
	public String deleteMyBook(@PathVariable("id") int  id)
	{
		service.deleteBookById(id);
		return "redirect:/available-books";
	}
	
	
	
}
