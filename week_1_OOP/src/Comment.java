
public class Comment {
	private String text;
	private IUser commentator;
	private Post post;
	
	public Comment(String text,IUser user,Post post){
		this.setText(text);
		this.setCommentator(user);
		this.setPost(post);
	}

	private void setText(String text) {
		this.text = text;
	}

	private void setCommentator(IUser commentator) {
		this.commentator = commentator;
	}

	private void setPost(Post post) {
		this.post = post;
	}
	
}
