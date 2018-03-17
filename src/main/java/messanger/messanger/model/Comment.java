package messanger.messanger.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import messanger.messanger.util.JsonDateSerializer;

public class Comment {
	private long id;
	private String comment;
	private Date createdDate;
	private String author;

	public Comment(){
		
	}
	
	public Comment(long id, String comment, Date createdDate, String author) {
		super();
		this.id = id;
		this.comment = comment;
		this.createdDate = createdDate;
		this.author = author;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", comment=" + comment + ", createdDate=" + createdDate + ", author=" + author
				+ "]";
	}
}
