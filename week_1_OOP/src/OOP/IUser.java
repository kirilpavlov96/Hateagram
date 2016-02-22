package OOP;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface IUser {

	Comment CreateComment(StringBuilder text, Post post);

	void addUserToFollow(IUser user);

	void addFollower(IUser user);

	String getName();

	int getAge();

	String getUsername();

	String getPassword();

	LocalDateTime getDateUserCreated();

	String getSex();

	StringBuilder getInfo();

	ArrayList<IUser> getFollowers();

	ArrayList<IUser> getFollowing();

	ArrayList<Post> getPosts();

	String getEmail();

}