package student.examples.repos;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import student.examples.domain.Message;
import student.examples.domain.MessageMapper;
import student.examples.domain.Subscriber;

@Repository
public class MessageRepo {
	
	@Autowired
	JdbcTemplate jdbc;
	
	@Autowired
	SubscriberRepo subscriberRepo;
	
	public void saveMessage(Message message) {
		String sql = "INSERT INTO public.messages(content) VALUES (?);";
		jdbc.update(sql, message.getContent());
	}
	
	public Message getMessageById(Integer id) {
		String sql = "SELECT * FROM public.messages WHERE id=" + id + ";";
		Message message = jdbc.query(sql, new MessageMapper()).get(0);
		return message;
	}
	
	public List<Message> getMessages() {
		String sql = "SELECT * FROM public.messages;";
		List<Message> messages = jdbc.query(sql, new MessageMapper());
		return messages;
	}
	
	public void validateMessageStatus(Integer subscriberId, Integer messageId) {
		String sql = "UPDATE public.subscriber_message SET sent=? WHERE subscriber_id=? AND message_id=?;";
		jdbc.update(sql, true, subscriberId, messageId);
	}
	
	public Map<Subscriber,Message> getNextUnsentMessage() throws NoUnsentMessageOrSubscriberToSendException {
		Map<Subscriber,Message> tuple = new HashMap<>();
		String sql = "SELECT subscriber_id,message_id FROM public.subscriber_message WHERE sent=false LIMIT 1 OFFSET 0;";
		SqlRowSet result = jdbc.queryForRowSet(sql);
		if (result.first()== false) {
			throw new NoUnsentMessageOrSubscriberToSendException("All messages are sent!");
		} else {
			result.first();
			Message message = getMessageById(result.getInt("message_id"));
			Subscriber subscriber = subscriberRepo.getSubscriberById(result.getInt("subscriber_id"));
			tuple.put(subscriber, message);
		}
		return tuple;
	}
	
	public class NoUnsentMessageOrSubscriberToSendException extends Exception {
		private static final long serialVersionUID = 1L;
		public NoUnsentMessageOrSubscriberToSendException(String errorMessage) {
			super(errorMessage);
		}
	}
	
}
