
public class User {
	private String name;
	private short age;
	private String username;
	private String password;
	private String email;
	private String sex;
	private String info;
	private User[] followers;
	private User[] following;
	
	public User(String name,short age,String username,String password,String email){
		this.setName(name);
		this.setAge(age);
		this.setUsername(username);
		this.setPassword(password);
		this.setEmail(email);
	}
	
	public void addUserToFollow(User user){
		//da se napravqt followers i following s kolekcii za da se dobavq
		//po lesno
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

	public void setAge(short age) {
		if(age<=120 && age>=0){
			this.age = age;
		}
		else{
			this.age=0;
		}
	}

	public void setUsername(String username) {
		//proverka dali username veche ne sushtestvuva
		if(username!=null){
			this.username = username;
		}
		else{
			//hvurlq checked exception
		}
	}

	public void setPassword(String password) {
		if(password!=null){
			this.password = password;
		}
		else{
			//hvurlq checked exception
		}
	}

	public void setEmail(String email) {
		if(email!=null){
			this.email = email;
		}
		else{
			//hvurlq checked exception
		}
	}

	public void setSex(String sex) {
		if(sex.equalsIgnoreCase("male")){
			this.sex = "Male";
		}
		else if(sex.equalsIgnoreCase("female")){
			this.sex="Female";
		}
		else{
			//hvurlq checked exception
		}
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
