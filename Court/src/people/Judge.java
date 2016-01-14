package people;

import java.util.Random;

public class Judge extends Jurist {
	
	private static final int MIN_YEARS_EXPERIENCE = 5;
	private static final int MIN_CASES = 0;
	private static final int MIN_SENTANCE = 3;
	private static final int MAX_SENTANCE = 40;

	public Judge(String name, int yearsExpirience, int cases) {
		super(name, yearsExpirience, cases, Judge.MIN_YEARS_EXPERIENCE, Judge.MIN_CASES);

	}
	
	public String defineSentance(){
		Random rnd = new Random();
		
		return String.valueOf(rnd.nextInt(Judge.MAX_SENTANCE - Judge.MIN_SENTANCE + 1) + Judge.MIN_SENTANCE);
	}
}
