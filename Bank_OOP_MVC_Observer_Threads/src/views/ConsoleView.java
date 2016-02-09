package views;

import interfaces.IView;

public class ConsoleView implements IView{

	@Override
	/**
	 * Write the output to the console
	 */
	public void render(String view) {
		System.out.println(view);
		System.out.println("-------------");
	}

}
