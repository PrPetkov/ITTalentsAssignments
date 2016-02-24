package interfaces;

public interface Takeable {
	void take(String takenBy);
	
	void returnBook();
	
	void CheckForDelay();
	
	String getType();
	
	String getName();
	
	double getTotalDebt();
	
	double getTax();

	boolean isTaken();
}
