package student.examples.tasks;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import student.examples.domain.Message;
import student.examples.domain.Subscriber;
import student.examples.repos.MessageRepo;
import student.examples.repos.MessageRepo.NoUnsentMessageOrSubscriberToSendException;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
@Component
public class SendEmailScheduler {
	
	@Autowired
	MessageRepo messageRepo;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Scheduled(fixedRate = 60000, initialDelay = 10000)
	public void sendEmail() {
		try {
			System.err.println("Preparing to send an email...");
			Map<Subscriber, Message> tuple = messageRepo.getNextUnsentMessage();
			System.err.println(tuple);
			SimpleMailMessage msg = new SimpleMailMessage();
			Subscriber subscriber = tuple.keySet().stream().findFirst().get();
			Message message = tuple.values().stream().findFirst().get();
			msg.setFrom("replyno790@gmail.com");
			msg.setTo(subscriber.getEmail());
			msg.setSubject("Newsletter");
			msg.setText(message.getContent());
			boolean success = false;
			try {
				mailSender.send(msg);
				success = true;
			} catch (MailException e) {
				e.printStackTrace();
			}
			if(success) {
				messageRepo.validateMessageStatus(subscriber.getId(), message.getId());
				System.err.println("Message was sent!");
			}
		} catch (NoUnsentMessageOrSubscriberToSendException e) {
			System.out.println("Waiting for an unsent message or a subscriber!");
		}
	}
}
