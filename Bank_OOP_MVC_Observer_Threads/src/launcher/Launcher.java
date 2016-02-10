package launcher;

import controlers.InitializeControler;
import interfaces.IView;
import views.*;

public class Launcher {
	/**
	 * The starting point of the program
	 * @param args
	 */
	public static void main(String[] args) {
		
		IView outputWriter = new ConsoleView();
		
		InitializeControler controler = new InitializeControler(outputWriter);
		controler.initialize();
	}

}
