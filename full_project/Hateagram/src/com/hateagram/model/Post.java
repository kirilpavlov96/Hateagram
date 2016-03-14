package com.hateagram.model;
import java.time.LocalDateTime;
import java.util.ArrayList;


public abstract class Post {
	private int ID;
	private String postType;
	private String UserID;
	private String postDate;
	private String filename;
	
	public Post(int iD,String postType, String userID, String postDate, String filename) {
		this.ID = iD;
		this.postType=postType;
		this.UserID = userID;
		this.postDate = postDate;
		this.filename = filename;
	}

	public int getID() {
		return ID;
	}

	public String getPostType() {
		return postType;
	}
	
	public String getUserID() {
		return UserID;
	}

	public String getPostDate() {
		return postDate;
	}

	public String getFilename() {
		return filename;
	}
	
	
}
