package workers;

import comparators.*;

import workers.Company.Departaments;

public class Demo {

	public static void main(String[] args) {

		Company cocaCola = new Company("CocaCola");
		
		cocaCola.addEmployee(new Employee("Petur", 18, 600, "01"), Departaments.Production);
		cocaCola.addEmployee(new Employee("Petur", 18, 800, "05"), Departaments.Production);
		cocaCola.addEmployee(new Employee("George", 40, 1400, "02"), Departaments.Sales);
		cocaCola.addEmployee(new Employee("Penka", 25, 1100, "03"), Departaments.Sales);
		cocaCola.addEmployee(new Employee("Alex", 17, 900, "04"), Departaments.Sales);
		
		cocaCola.printEmployees();
		
		System.out.println("Sorted by salary --------------");
		System.out.println(cocaCola.getSortedEmployees(new SalaryComparator()));
		System.out.println("Sorted by lexicographical order");
		System.out.println(cocaCola.getSortedEmployees(new LexicographicalComparator()));
		System.out.println("Sorted by age -----------------");
		System.out.println(cocaCola.getSortedEmployees(new AgeComparator()));
		
		System.out.println("All Employees -----------------");
		System.out.println(cocaCola.getUnitedDepartaments());

		cocaCola.removeDuplicates();
		
		System.out.println("Employees by departament without repetitions");		
		cocaCola.printEmployees();
		
	}

}
