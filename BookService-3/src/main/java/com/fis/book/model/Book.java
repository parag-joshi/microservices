package com.fis.book.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BOOK")
public class Book 
{
	
	@Id
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "bookid")
	private String bookId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "availablecopies")
	private int availableCopies;
	
	@Column(name = "totalcopies")
	private int totalCopies;
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getBookId() 
	{
		return bookId;
	}

	public void setBookId(String bookId) 
	{
		this.bookId = bookId;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getAuthor() 
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public int getAvailableCopies() 
	{
		return availableCopies;
	}

	public void setAvailableCopies(int availableCopies) 
	{
		this.availableCopies = availableCopies;
	}

	public int getTotalCopies() 
	{
		return totalCopies;
	}

	public void setTotalCopies(int totalCopies) 
	{
		this.totalCopies = totalCopies;
	}
	
}
