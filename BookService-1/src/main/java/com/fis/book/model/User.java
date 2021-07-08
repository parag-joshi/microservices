package com.fis.book.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "USER")
public class User 
{

	@Id
	@Column(name = "id", nullable = false)
	private String id;
	
	@Column(name = "subscribername")
	private String subscriberName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "email")
	private String email;

	public String getId() 
	{
		return id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getSubscriberName() 
	{
		return subscriberName;
	}

	public void setSubscriberName(String subscriberName) 
	{
		this.subscriberName = subscriberName;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}
}
