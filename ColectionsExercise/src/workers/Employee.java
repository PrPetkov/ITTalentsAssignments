package workers;

import java.util.HashMap;

public class Employee {

//	enum Months{
//		January,
//		February,
//		March,
//		April,
//		May,
//		June,
//		July,
//		Aughust,
//		September,
//		October,
//		November,
//		December
//	}
	
	private String name;
	private int age;
	private double salary;
	private String idNumber;
//	private HashMap<Months, Integer> monthlySalary;
	
	public Employee(String name, int age, double salary, String idNumber) {
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.idNumber = idNumber;
//		this.monthlySalary = new HashMap<>();
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public double getSalary() {
		return salary;
	}

	public String getIdNumber() {
		return idNumber;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", idNumber=" + idNumber + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
//name, age, salary and an ID number