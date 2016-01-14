package people;

public class Lawyer extends Jurist {
	
	private static final int MIN_YEARS_EXPERIENCE = 0;
	private static final int MIN_CASES = 10;

	public Lawyer(String name, int yearsExpirience, int cases) {
		super(name, yearsExpirience, cases, Lawyer.MIN_YEARS_EXPERIENCE, Lawyer.MIN_CASES);
		
	}

}
