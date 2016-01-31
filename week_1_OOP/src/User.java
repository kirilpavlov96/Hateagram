import java.util.ArrayList;

public class User implements IUser{
	private static short MINIMAL_AGE;
	private static short MAXIMAL_AGE;
private static long NUMBER_OF_USERS=0;
	
	private String name;
	private int age;
	private String username;
	private String password;
	private String email;
	
	private String sex;
	private String info;
	private ArrayList<IUser> followers;
	private ArrayList<IUser> following;
	
	public User(String name,int age,String username,String password,String email){
		this.setName(name);
		this.setAge(age);
		this.setUsername(username);
		this.setPassword(password);
		this.setEmail(email);
		NUMBER_OF_USERS++;
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
			this.name="Unnamed";
		}
	}

	public void setAge(int age) {
		if(age<= MAXIMAL_AGE && age>=MINIMAL_AGE){
			this.age = age;
		}
		else{
			this.age=0;
		}
	}

	public void setUsername(String username) {
		if(username!=null ){
			this.username = username;
		}else{
			this.username=Long.toString(NUMBER_OF_USERS);
		}
	}

	public void setPassword(String password) {
			this.password = password;
	}

	public void setEmail(String email) {
		if(isValidEmailAddress(email)){
			this.email = email;
		}else{
			this.email = "no email available";
		}
	}
	
	public static boolean isValidEmailAddress(String email) {
	    boolean stricterFilter = true; 
	    String stricterFilterString = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
	    String laxString = ".+@.+\\.[A-Za-z]{2}[A-Za-z]*";
	    String emailRegex = stricterFilter ? stricterFilterString : laxString;
	    java.util.regex.Pattern p = java.util.regex.Pattern.compile(emailRegex);
	    java.util.regex.Matcher m = p.matcher(email);
	    return m.matches();
	}

	public void setSex(String sex) {
		if(sex.equalsIgnoreCase("male")){
			this.sex = "Male";
		}
		else if(sex.equalsIgnoreCase("female")){
			this.sex="Female";
		}
		else{
			this.sex="undefined";
		}
	}

	public void setInfo(String info) {
		if(info==null)
			this.info="";
		else{
			this.info = info;
		}
	}

	String getEmail() {
		return email;
	}

}
