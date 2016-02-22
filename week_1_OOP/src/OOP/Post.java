package OOP;
import java.time.LocalDateTime;
import java.util.ArrayList;


public abstract class Post {
	private String path;
	private IUser owner;
	private ArrayList<IUser> likes;
	private ArrayList<IUser> disLikes;
	private ArrayList<Comment> comments;
	private final LocalDateTime dateAdded;
	private static long counter=1;
	
	public Post(String path,IUser user){
		this.path=path;
		this.owner = user;
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

	public IUser getOwner(){
		return this.owner;
	}
	public long getCounter(){
		return counter++;
	}
	public String getLastComment(){
		return this.Comment.indexOf(Comment.getLength()-1);
	}
	
}
