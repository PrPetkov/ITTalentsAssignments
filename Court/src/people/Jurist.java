package people;

import outputBuilder.OutpuBuilder;

public abstract class Jurist {

	private final int minYearsExpirience;
	private final int minCases;
	
	private String name;
	@SuppressWarnings("unused")
	private int yearsExpirience;
	private int cases;
	
	
	public Jurist(String name, int yearsExpirience, int cases, int minYearsExpirience, int minCases) {
		this.minYearsExpirience = minYearsExpirience;
		this.minCases = minCases;
		
		this.setName(name);
		this.setYearsExpirience(yearsExpirience);
		this.setCases(cases);
	}
	
	public String getName() {
				
		return this.name;
	}
	
	protected void setName(String name){
		if (name == null) {
			throw new IllegalArgumentException("Name can not be null");
		}
		
		this.name = name;
		
	}
	
	public void setYearsExpirience(int yearsExpirience){
		if (yearsExpirience < this.minYearsExpirience) {
			throw new IllegalArgumentException("Expirience must be at least " + this.minYearsExpirience + " years!");
		}
		
		this.yearsExpirience = yearsExpirience;
	}
	
 	public void setCases(int cases){
		if (cases < this.minCases){
			throw new IllegalArgumentException("Cases must be at least " + this.minCases);
		}
		
		this.cases = cases;
	}
 	
 	public int getCases(){
 		return this.cases;
 	}

 	public void addCase(){
 		this.cases++;
 	}
 	
	public String askQuestion(Citizen citizen){
		//generate text for the question
		StringBuilder builder = new StringBuilder();
		builder.append("I, ");
		builder.append(this.name);
		builder.append(", am asking ");
		builder.append(citizen.getName());
		builder.append(" a qestion.");
		
		return builder.toString();		
	}
	
	public void takeNotes(String text, String file){
		//write to the court archive
		OutpuBuilder.appendLine(text + System.lineSeparator(), file);
	}
	
}
