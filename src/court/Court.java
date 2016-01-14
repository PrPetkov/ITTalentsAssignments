package court;

import java.util.HashSet;

import cases.Case;
import people.*;

public class Court {

	private String name;
	private String adress;
	private HashSet<Jurist> jurists;
	private HashSet<Case> cases;
	
	public Court(String name, String adress, HashSet<Jurist> jurists, HashSet<Case> cases) {
		this.name = name;
		this.adress = adress;
		this.jurists = jurists;
		this.cases = cases;
	}

	public String getName() {
		return name;
	}

	public String getAdress() {
		return adress;
	}

	public HashSet<Jurist> getJurists() {
		return jurists;
	}

	public HashSet<Case> getCases() {
		return cases;
	}
	
	public void executeCase(Case aCase){
		aCase.execute();
	}
	
}
