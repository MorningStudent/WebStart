package student.examples.repos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import student.examples.domain.Message;
import student.examples.domain.MessageMapper;
import student.examples.domain.Subscriber;
import student.examples.domain.SubscriberMapper;

@Repository
public class SubscriberRepo {
	
	@Autowired
	JdbcTemplate jdbc;
	
	public List<Subscriber> getSubscribers() {
		String sql = "SELECT * FROM public.subscribers;";
		List<Subscriber> subscribers = jdbc.query(sql, new SubscriberMapper());
		return subscribers;
	}
	
	public Subscriber getSubscriberById(Integer id) {
		String sql = "SELECT * FROM public.subscribers WHERE id=" + id + ";";
		Subscriber subscriber = jdbc.query(sql, new SubscriberMapper()).get(0);
		return subscriber;
	}
	
	public List<String> getSubscribersEmailsByIds(List<Integer> ids) {
		String idValues = "";
		//ids.forEach(id -> idValues += id + ",");
		for (Integer id : ids) {
			idValues += id + ",";
		}
		idValues = idValues.substring(0, idValues.length()-1);
		String sql = "SELECT email FROM public.subscribers WHERE id IN ( "+idValues+" );";
		return jdbc.queryForList(sql, String.class);
	}
	
	public void save(Subscriber subscriber) {
		String sql = "INSERT INTO public.subscribers(name, email) VALUES (?, ?);";
		jdbc.update(sql, subscriber.getName(), subscriber.getEmail());
	}
	
	public void updateName(String email, String newName) {
		String sql = "UPDATE public.subscribers SET name=? WHERE email=?;";
		jdbc.update(sql, newName, email);
	}
	
	public void removeById(int id) {
		String sql = "DELETE FROM public.subscribers WHERE id=?;";
		jdbc.update(sql, id);
	}
	
}
