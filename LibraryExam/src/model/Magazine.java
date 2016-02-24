package model;

import java.time.LocalDateTime;


public class Magazine extends LibraryEntry{

	private int number;
	private LocalDateTime madeOn;
	
	public Magazine(String name, String publisher, String type, int number, LocalDateTime madeOn) {
		super(name, publisher, type);
		
		this.number = number;
		this.madeOn = madeOn;
	}

	public int getNumber() {
		return number;
	}

	public LocalDateTime getMadeOn() {
		return madeOn;
	}
	
}
