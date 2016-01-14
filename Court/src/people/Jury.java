package people;

import java.util.Random;

public class Jury extends Jurist {

	private static final int MIN_YEARS_EXPERIENCE = 0;
	private static final int MIN_CASES = 0;
	
	public Jury(String name, int yearsExpirience, int cases) {
		super(name, yearsExpirience, cases, Jury.MIN_YEARS_EXPERIENCE, Jury.MIN_CASES);
		
	}
	
	public boolean defineGuilty(){
		Random rnd = new Random();
		
		return rnd.nextBoolean();
	}
}
