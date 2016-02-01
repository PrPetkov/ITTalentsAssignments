package comparators;

import java.util.Comparator;

import workers.Employee;

public class AgeComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		if (o1.getAge() == o2.getAge()) {
			return 0;
		}
		
		return (o1.getAge() > o2.getAge()) ? 1 : -1;
	}

	
}
