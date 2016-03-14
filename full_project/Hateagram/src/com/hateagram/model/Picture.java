package com.hateagram.model;

public class Picture extends Post{

	public Picture(int iD, String userID, String postDate, String filename){
		super(iD, "picture", userID, postDate, filename);
	}

}
