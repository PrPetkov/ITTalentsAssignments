package comparators;

import java.util.Comparator;

import workers.Employee;

public class LexicographicalComparator implements Comparator<Employee>{

	@Override
	public int compare(Employee o1, Employee o2) {
		return o1.getName().compareTo(o2.getName());
	}



}
