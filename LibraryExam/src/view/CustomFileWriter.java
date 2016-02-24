package view;

import interfaces.IOutputBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CustomFileWriter implements IOutputBuilder {

	private String path;

	public CustomFileWriter(String path) {
		super();
		this.path = path;
	}
	
	public void writeLine(String text){
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.path, true))){
			writer.append(text);
			writer.append(System.lineSeparator());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
