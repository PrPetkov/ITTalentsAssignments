package people;

public abstract class Citizen {

	private String name;
	private String address;
	private int age;
	
	
	
	public Citizen(String name, String address, int age) {

		this.setName(name);
		this.setAddress(address);
		this.setAge(age);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Name can not be null");
		}
		
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		if (address == null) {
			throw new IllegalArgumentException("Address can not be null");
		}
		
		this.address = address;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		if (age <= 0) {
			throw new IllegalArgumentException("Age must be positive");
		}
		
		this.age = age;
	}
	
	
	
}
