import java.util.ArrayList;

public abstract class Post {
	private String path;
	private ArrayList<IUser> likes;
	private ArrayList<IUser> disLikes;
	private ArrayList<Comment> comments;
	
	public abstract void addComment(Comment comment);
	public abstract void addLike(IUser user);
	public abstract void addDisLike(IUser user);
	public void setPath(String path){
		//implementation...
	}
}
