package people;

import java.util.HashSet;

public abstract class CitizenWithLawyer extends Citizen {

	private HashSet<Lawyer> lawyers;
	
	public CitizenWithLawyer(String name, String address, int age, HashSet<Lawyer> lawyers) {
		super(name, address, age);
		
		this.setLawyers(lawyers);

	}

	public HashSet<Lawyer> getLawyers() {
		return lawyers;
	}

	protected void setLawyers(HashSet<Lawyer> lawyers) {
		this.lawyers = lawyers;
	}
	
	public void addLawyer(Lawyer lawyer){
		if (lawyer == null) {
			throw new IllegalArgumentException("Lawyer can not be null");
		}
		
		this.lawyers.add(lawyer);
	}
}
