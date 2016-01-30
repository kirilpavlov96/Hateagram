import java.util.ArrayList;

public class User implements IUser{
	private String name;
	private int age;
	private String username;
	private String password;
	private String email;
	private String sex;
	private String info;
	private ArrayList<IUser> followers;
	private ArrayList<IUser> following;
	
	public User(String name,short age,String username,String password,String email){
		this.setName(name);
		this.setAge(age);
		this.setUsername(username);
		this.setPassword(password);
		this.setEmail(email);
	}
	
	public void addUserToFollow(IUser user){
		if(user==null) return;
		this.following.add(user);
		user.addFollower(user);
	}
	
	public void addFollower(IUser user){
		if(user==null) return;
		this.followers.add(user);
	}
	
	public void addInfo(String info){
		//da se napravi info sus string builder za po golqma
		//funkcionalnost
	}

	public void setName(String name) {
		if(name!=null){
			this.name = name;
		}
		else{
			this.name="Unndamed";
		}
	}

	public void setAge(short age) {
		if(age<=120 && age>=0){
			this.age = age;
		}
		else{
			this.age=0;
		}
	}

	public void setUsername(String username) {
			this.username = username;
	}

	public void setPassword(String password) {
			this.password = password;
	}

	public void setEmail(String email) {
			this.email = email;
	}

	public void setSex(String sex) {
		if(sex.equalsIgnoreCase("male")){
			this.sex = "Male";
		}
		else if(sex.equalsIgnoreCase("female")){
			this.sex="Female";
		}
		else{
			this.sex = "Male";
		}
	}

	public void setInfo(String info) {
		if(info==null)
			this.info="";
		else{
			this.info = info;
		}
	}

}
