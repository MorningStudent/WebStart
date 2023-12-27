package student.examples.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MessageMapper implements RowMapper<Message> {

	@Override
	public Message mapRow(ResultSet rs, int numRow) throws SQLException {
		Message message = new Message();
		message.setId(rs.getInt("id"));
		message.setContent(rs.getString("content"));
		return message;
	}

}
