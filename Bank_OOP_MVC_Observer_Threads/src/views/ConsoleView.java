package views;

import Interfaces.IView;

public class ConsoleView implements IView{

	@Override
	public void render(String view) {
		System.out.println(view);
		System.out.println("-------------");
	}

}
