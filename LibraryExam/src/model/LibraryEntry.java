package model;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class LibraryEntry {

	private String name;
	private String publisher;
	private String type;


	
	public LibraryEntry(String name, String publisher, String type) {
		this.name = name;
		this.publisher = publisher;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getType() {
		return type;
	}

}
