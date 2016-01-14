package people;

import java.util.HashSet;

public class Defendant extends CitizenWithLawyer{

	public Defendant(String name, String address, int age, HashSet<Lawyer> lawyers) {
		super(name, address, age, lawyers);
		
	}

}
