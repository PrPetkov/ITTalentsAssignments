package people;

import java.util.HashSet;

import interfaces.IProsecutor;

public class Prosecutor extends CitizenWithLawyer implements IProsecutor {

	public Prosecutor(String name, String address, int age, HashSet<Lawyer> lawyers) {
		super(name, address, age, lawyers);
		
	}

}
