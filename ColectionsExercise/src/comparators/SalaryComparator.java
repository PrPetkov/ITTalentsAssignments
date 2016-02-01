package comparators;

import java.util.Comparator;

import workers.Employee;

public class SalaryComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		if (o1.getSalary() == o2.getSalary()) {
			return 0;
		}
		
		return (o1.getSalary() > o2.getSalary()) ? 1 : -1;
	}

}
