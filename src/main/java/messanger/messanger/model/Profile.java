package messanger.messanger.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Profile {
	private Long id;
	private String firstName;
	private String lastName;
	private Date created;

	public Long getId() {
		return id;
	}

	public Profile(Long id, String firstName, String lastName, Date created) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.created = created;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}
