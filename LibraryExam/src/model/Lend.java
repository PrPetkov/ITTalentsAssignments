package model;

import java.time.LocalDateTime;

public class Lend {
	
	private LocalDateTime takenOn;
	private LocalDateTime returnedOn;
	
	public Lend(LocalDateTime takenOn) {

		this.takenOn = takenOn;
	}

	public LocalDateTime getTakenOn() {
		return takenOn;
	}

	public LocalDateTime getReturnedOn() {
		return returnedOn;
	}

	public void setReturnedOn(LocalDateTime returnedOn) {
		this.returnedOn = returnedOn;
	}
	
	
}
