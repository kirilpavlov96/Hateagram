import java.time.LocalDateTime;
import java.util.ArrayList;


public abstract class Post {
	private String path;
	private ArrayList<IUser> likes;
	private ArrayList<IUser> disLikes;
	private ArrayList<Comment> comments;
	private final LocalDateTime dateAdded;
	
	public Post(String path){
		this.path=path;
		this.dateAdded= LocalDateTime.now();
	}
	
	public LocalDateTime getDateAdded(){
		return this.dateAdded;
	}
	
	public void addComment(Comment comment){
		if(comment!=null)
		this.comments.add(comment);
	}
	public void addLike(IUser user){
		if(user!=null)
		this.likes.add(user);
	}
	public void addDisLike(IUser user){
		if(user!=null)
		this.disLikes.add(user);
	}

}
