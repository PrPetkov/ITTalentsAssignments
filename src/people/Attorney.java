package people;

import interfaces.IProsecutor;

public class Attorney extends Jurist implements IProsecutor {
	
	private static final int MIN_YEARS_EXPERIENCE = 10;
	private static final int MIN_CASES = 0;

	public Attorney(String name, int yearsExpirience, int cases) {
		super(name, yearsExpirience, cases, Attorney.MIN_YEARS_EXPERIENCE, Attorney.MIN_CASES);
		
	}

}
