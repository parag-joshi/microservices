package com.fis.subscription.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SUBSCRIPTION")
public class Subscription
{
	@Id
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "subscribername")
	private String subscriberName;

	@Column(name = "datesubscribed")
	private Date dateSubscribed;

	@Column(name = "datereturned")
	private Date dateReturned;

	@Column(name = "bookid")
	private String bookId;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
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

	public Date getDateSubscribed()
	{
		return dateSubscribed;
	}

	public void setDateSubscribed(Date dateSubscribed)
	{
		this.dateSubscribed = dateSubscribed;
	}

	public Date getDateReturned()
	{
		return dateReturned;
	}

	public void setDateReturned(Date dateReturned)
	{
		this.dateReturned = dateReturned;
	}

	public String getBookId()
	{
		return bookId;
	}

	public void setBookId(String bookId)
	{
		this.bookId = bookId;
	}
}
