package student.examples.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import student.examples.domain.Subscriber;
import student.examples.repos.SubscriberRepo;

@RestController
public class SubscriberController {
	
	@Autowired
	SubscriberRepo sr;
	
	@GetMapping("/subscribers")
	public List<Subscriber> subscribersIndex() {
		List<Subscriber> subscribers = sr.getSubscribers();
		return subscribers;
	}
	
	@GetMapping("/subscribers/add/{email}/{name}")
	public String subscriberAdd(@PathVariable String email, @PathVariable String name) {
		sr.save(new Subscriber(name, email));
		return "Subscriber added";
	}
	
	@GetMapping("/subscribers/setname/{email}/{newName}")
	public String subscriberSetName(@PathVariable String email, @PathVariable String newName) {
		sr.updateName(email, newName);
		return "New Name set";
	}
	
}
