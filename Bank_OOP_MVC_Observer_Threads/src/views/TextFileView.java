package views;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import interfaces.IView;

public class TextFileView implements IView {

	String path;
	
	public TextFileView(String path) {
		File file = new File(path);
		//Wipes the data from previous runs
		if (file.exists()) {
			file.delete();
		}
		
		this.path = path;
	}

	@Override
	public void render(String view) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(this.path, true))) {
			writer.append(view + System.lineSeparator() + "----------------" + System.lineSeparator());
			
		} catch (IOException e) {
			this.render(e.getMessage());
		}
	}
}
