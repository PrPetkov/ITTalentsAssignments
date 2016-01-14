package demo;

import java.util.HashSet;

import cases.Case;
import cases.CivilCase;
import cases.CriminalCase;
import people.*;

public class Demo {

	public static void main(String[] args) {
		
		HashSet<Jury> jury = new HashSet<>();
		
		jury.add(new Jury("kaka penka", 0, 1));
		jury.add(new Jury("kaka ginka", 2, 1));
		jury.add(new Jury("kaka lara", 0, 0));
		
		HashSet<Witness> witneses = new HashSet<>();
		
		witneses.add(new Witness("dido", "", 10));
		
		HashSet<Lawyer> lawyers = new HashSet<>();
		
		lawyers.add(new Lawyer("chicho gosho", 5, 50));
		
		try {
			Case someCase = new CivilCase(new Judge("Pesho", 6, 20), jury, new Defendant("pencho", "", 15, lawyers),
					new Prosecutor("gencho", "", 50, lawyers), witneses, "case01.txt");
			
			someCase.execute();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		lawyers.add(new Lawyer("skup advokat", 30, 1001));
		
		try {
			Case anotherCase = new CriminalCase(new Judge("kaka qneva", 10, 101), jury, new Defendant(
					"don korleone", "unknown", 70, lawyers), new Attorney("s.c.", 10, 5), new HashSet<>(), "case02.txt");
			
			anotherCase.execute();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		
	}

}
