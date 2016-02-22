package OOP;
import java.time.LocalDateTime;

public class Comment {
	private StringBuilder text;
	private IUser commentator;
	private Post post;
	private final LocalDateTime dateAdded;
	
	public Comment(StringBuilder text,IUser user,Post post){
		this.setText(text);
		this.setCommentator(user);
		this.setPost(post);
		this.dateAdded=LocalDateTime.now();
	}
	
	public LocalDateTime getDateAdded(){
		return this.dateAdded;
	}

	private void setText(StringBuilder text) {
		if(text!=null)
		this.text = text;
	}

	private void setCommentator(IUser commentator) {
		if (commentator!=null)
		this.commentator = commentator;
	}

	private void setPost(Post post) {
		if(post!=null)
		this.post = post;
	}
	
}
