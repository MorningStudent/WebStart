package student.examples.domain;

public class Message {
	
	private Integer id;
	private String content;
	
	public Message() {
	}

	public Message(String content) {
		this.content = content;
	}

	public Message(Integer id, String content) {
		this.id = id;
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Message [content=" + content + "]";
	}
	
}
