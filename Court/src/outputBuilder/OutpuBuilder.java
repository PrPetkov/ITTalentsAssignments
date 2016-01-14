package outputBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public abstract class OutpuBuilder {

	public static void appendLine(String text, String file){
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))){
			writer.append(text + System.lineSeparator());
						
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("problem");
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("problem2");
		}
	}

}
