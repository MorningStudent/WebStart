package student.examples.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import student.examples.domain.Subscriber;
import student.examples.repos.SubscriberRepo;

@Controller
public class AdminController {
	
	@Autowired
	SubscriberRepo sr;
	
	@PostMapping("/admin/subscribers/remove")
	public String adminSubscriberRemove(@RequestParam int id) {
		//sr.removeById(id);
		return "Subscriber " + id + " removed";
	}
	
	@GetMapping("/admin/subscribers")
	public String subscribersIndex(Model model) {
		model.addAttribute("subscribers", sr.getSubscribers());
		return "admin/subscribers";
	}
	
	@GetMapping("/admin/subscribers/compose")
	public String adminSubscribersCompose(@RequestParam() List<Integer> subscribers) {
		List<String> emails = sr.getSubscribersEmailsByIds(subscribers);
		System.out.println(emails);
		return "admin/compose";
	}
	
//	@GetMapping("/subscribers/add/{email}/{name}")
//	public String subscriberAdd(@PathVariable String email, @PathVariable String name) {
//		sr.save(new Subscriber(name, email));
//		return "Subscriber added";
//	}
//	
//	@GetMapping("/subscribers/setname/{email}/{newName}")
//	public String subscriberSetName(@PathVariable String email, @PathVariable String newName) {
//		sr.updateName(email, newName);
//		return "New Name set";
//	}
	
}
