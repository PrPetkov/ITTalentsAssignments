package launcher;

import controlers.InitializeControler;

public class Launcher {
	/**
	 * The starting point of the program
	 * @param args
	 */
	public static void main(String[] args) {
		
		InitializeControler controler = new InitializeControler();
		controler.initialize();
	}

}
