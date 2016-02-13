package model;

public class MusicInstrument {
	
	private String name;
	private double price;
	
	public MusicInstrument(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return this.name;
	}

	public double getPrice() {
		return this.price;
	}
}
