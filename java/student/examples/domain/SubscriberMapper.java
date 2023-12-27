package student.examples.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class SubscriberMapper implements RowMapper<Subscriber> {

	@Override
	public Subscriber mapRow(ResultSet resultSet, int i) throws SQLException {
		Subscriber subscriber = new Subscriber();
		subscriber.setId(resultSet.getInt("id"));
		subscriber.setName(resultSet.getString("name"));
		subscriber.setEmail(resultSet.getString("email"));
		return subscriber;
	}

}
