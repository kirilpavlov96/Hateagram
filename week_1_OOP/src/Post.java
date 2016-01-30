import java.util.ArrayList;

public abstract class Post {
	private String path;
	private ArrayList<IUser> likes;
	private ArrayList<IUser> disLikes;
	private ArrayList<Comment> comments;
	
	public Post(String path){
		this.path=path;
	}
	
	public void addComment(Comment comment){
		this.comments.add(comment);
	}
	public void addLike(IUser user){
		this.likes.add(user);
	}
	public void addDisLike(IUser user){
		this.disLikes.add(user);
	}

}
