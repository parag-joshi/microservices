package com.fis.book.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fis.book.model.Book;
import com.fis.book.repository.BookRepository;

@RestController
public class BookResource 
{
	@Value ("${server.port}")
	private String portNumber;
	
	@Autowired
	private BookRepository bookRepository;
	
	@GetMapping("/books")
	public ResponseEntity<?> getBooks()
	{
		List<Book> listOfbooks = (java.util.List<Book>) bookRepository.findAll();
		
		if (listOfbooks.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		System.out.println("The service is running on port: " + portNumber);
		
		return new ResponseEntity<>(listOfbooks, HttpStatus.OK);
	}
	
	@GetMapping("/books/{bookId}")
	public ResponseEntity<?> getBookById(@PathVariable(value="bookId") String bookId)
	{
		Optional<Book> book = bookRepository.findByBookId(bookId);
		if (book.isPresent())
			return new ResponseEntity<>(book, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/available-copies/{bookId}")
	public ResponseEntity<?> getAvailableCopiesByBookId(@PathVariable(value="bookId") String bookId)
	{
		Optional<Book> book = bookRepository.findByBookId(bookId);
		if (book.isPresent())
			return new ResponseEntity<>(book.get().getAvailableCopies(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	private boolean isValidId(String id)
	{
		try
		{
			Integer.parseInt(id);
		} 
		catch (NumberFormatException ignored)
		{
			return false;
		}
		return true;
	}
	
	@PostMapping("/books/{bookId}/{userAction}")
	public ResponseEntity<?> updateBookDetails(@PathVariable(value="bookId") String bookId, @PathVariable(value="userAction") String userAction)
	{
		Optional<Book> optionalBook = bookRepository.findByBookId(bookId);
		if (!optionalBook.isPresent())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		Book book = optionalBook.get();
		int availableCopies = book.getAvailableCopies();
		
		if (!isValidId(userAction))
			return new ResponseEntity<>("userAction" + userAction + "is not a valid action", HttpStatus.BAD_REQUEST);
		
		switch(userAction) 
		{
			case "1":
				// subscribe
				if (availableCopies > 0) 	
				{
					book.setAvailableCopies(availableCopies - 1);
					bookRepository.save(book);
				}
				else 
				{
					return new ResponseEntity<>("No copies available for this book.", HttpStatus.BAD_REQUEST);
				}
				break;
			case "-1":
				// return
				book.setAvailableCopies(availableCopies + 1);
				bookRepository.save(book);
			break;
			default:
				return new ResponseEntity<>("Incorrect userAction.", HttpStatus.BAD_REQUEST);
		}
	
		return new ResponseEntity<>(book, HttpStatus.OK);
				
	}

}
