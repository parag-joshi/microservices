package com.fis.subscription.resource;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fis.subscription.model.Subscription;
import com.fis.subscription.repository.SubscriptionRepository;

@RestController
public class SubscriptionResource 
{
	@Value ("${server.port}")
	private String portNumber;
	
	@Autowired
    RestTemplate restTemplate;
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@GetMapping("/subscriptions")
	public ResponseEntity<?> getSubscriptions(@RequestParam (required = false) String subscriberName)
	{
		List<Subscription> listOfLibrarySubscriptions;
		if (subscriberName != null)
			listOfLibrarySubscriptions = (List<Subscription>) subscriptionRepository.findBySubscriberName(subscriberName);
		else
			listOfLibrarySubscriptions = (List<Subscription>) subscriptionRepository.findAll();
		
		if (listOfLibrarySubscriptions.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		System.out.println("The service is running on port: " + portNumber);
		
		return new ResponseEntity<>(listOfLibrarySubscriptions, HttpStatus.OK);
	}
	
	@PostMapping("/subscriptions")
	private ResponseEntity<Subscription> saveSubscription(@RequestBody Subscription librarySubscription) 
	{
		String bookId = librarySubscription.getBookId();
		int availableCopies = checkAvailableCopiesWithServiceDiscovery(bookId);
		if (availableCopies <= 0)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		ResponseEntity<Subscription> newSubscription = new ResponseEntity<Subscription>(subscriptionRepository.save(librarySubscription), HttpStatus.CREATED);
		updateAvailableCopiesWithServiceDiscovery(bookId, "1");
		return newSubscription;
	}
	
	@PostMapping("/returns")
	private ResponseEntity<Subscription> updateSubscription(@RequestBody Subscription librarySubscription) 
	{
		Subscription existingLibrarySubscription = (Subscription) subscriptionRepository.findBySubscriberNameAndBookId(librarySubscription.getSubscriberName(), librarySubscription.getBookId());
		existingLibrarySubscription.setDateReturned(librarySubscription.getDateReturned());
		return new ResponseEntity<Subscription>(subscriptionRepository.save(existingLibrarySubscription), HttpStatus.CREATED);
	}
	
	private int checkAvailableCopiesWithServiceDiscovery(String bookId) 
	{
		String uri = "http://BookService/available-copies/{bookId}";
		String availableCopies = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, bookId).getBody();
		return Integer.parseInt(availableCopies);
	}
	
	private void updateAvailableCopiesWithServiceDiscovery(String bookId, String userAction) 
	{
		String uri = "http://BookService/books/{bookId}/{userAction}";
		restTemplate.exchange(uri, HttpMethod.POST, null, new ParameterizedTypeReference<String>() {}, bookId, userAction);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() 
	{
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(30000);
        httpRequestFactory.setConnectTimeout(30000);
        httpRequestFactory.setReadTimeout(30000);
        return new RestTemplate(httpRequestFactory);
	}
	
}
