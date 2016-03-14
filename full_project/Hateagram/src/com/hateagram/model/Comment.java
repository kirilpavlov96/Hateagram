package com.hateagram.model;
import java.time.LocalDateTime;

public class Comment {
	private int ID;
	private String commentText;
	private int postID;
	private String userID;
	
	public Comment(int iD, String commentText, int postID, String userID) {
		super();
		ID = iD;
		this.commentText = commentText;
		this.postID = postID;
		this.userID = userID;
	}

	public int getID() {
		return ID;
	}

	public String getCommentText() {
		return commentText;
	}

	public int getPostID() {
		return postID;
	}

	public String getUserID() {
		return userID;
	}
	
	
}
