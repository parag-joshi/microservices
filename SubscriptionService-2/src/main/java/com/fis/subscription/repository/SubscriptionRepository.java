package com.fis.subscription.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.fis.subscription.model.Subscription;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer>
{
	
	List<Subscription> findBySubscriberName(String subscriberName);

	@Query("select s from Subscription s where s.subscriberName = :subscriberName and s.bookId = :bookId")
	Subscription findBySubscriberNameAndBookId(String subscriberName, String bookId);
}
