package thinkinjava.annotation;

@DBTable(name="MEMBER")
public class Member {

	@SQLString(30)
	String firstName;
	
	@SQLString(value=50)
	String lastName;
	
	@SQLInteger
	Integer age;
	
	@SQLString(value=30, constrains=@Constraints(primaryKey=true))
	String handle;
	
	static int memberCount;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
}
