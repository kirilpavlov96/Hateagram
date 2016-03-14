package com.hateagram.model;

public class Video extends Post{

	public Video(int iD, String userID, String postDate, String filename){
		super(iD, "video", userID, postDate, filename);
	}
	
}
