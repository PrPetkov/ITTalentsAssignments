package workers;

import java.util.*;

import comparators.LexicographicalComparator;

public class Company {
	
	enum Departaments {Sales, Production}
	private String name;
	private HashMap<Departaments, ArrayList<Employee>> employees;
	
	public Company(String name) {
		this.name = name;
		this.employees = new HashMap<>();
	}

	public String getName() {
		return name;
	}
	
	
	
	public HashMap<Departaments, ArrayList<Employee>> getSortedEmployees(Comparator<Employee> comparator) {
		HashMap<Departaments, ArrayList<Employee>> sortedEmployees = new HashMap<>(this.employees);
		
		for (Departaments departament : sortedEmployees.keySet()) {
			sortedEmployees.get(departament).sort(comparator);
		}
		
		return sortedEmployees;
	}

	public void addEmployee(Employee employee, Departaments departament){
		if (!employees.containsKey(departament)) {
			employees.put(departament, new ArrayList<Employee>());
		}
		
		employees.get(departament).add(employee);
	}
	
	public void printEmployees(){
		System.out.println(this.employees);
	}
	
	public ArrayList<Employee> getUnitedDepartaments(){
		ArrayList<Employee> unitedEmployees = new ArrayList<>();
		
		for (Departaments departament : this.employees.keySet()) {
			unitedEmployees.addAll(this.employees.get(departament));
		}
		
		unitedEmployees.sort(new LexicographicalComparator());
		
		return unitedEmployees;
	}
	
	public void removeDuplicates(){
		for (Departaments departament : this.employees.keySet()) {
			HashSet<Employee> helperSet = new HashSet<>(this.employees.get(departament));
			this.employees.put(departament, new ArrayList<>(helperSet));
		}
	}
}
