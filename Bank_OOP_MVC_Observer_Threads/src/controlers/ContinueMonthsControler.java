package controlers;

import java.util.ArrayList;

import interfaces.IView;
import model.*;

public class ContinueMonthsControler {
	
	private Bank bank;
	private IView viewRenderer;
	private ArrayList<Client> clients;
	private Time time;
	
	public ContinueMonthsControler(Bank bank, IView viewRenderer, ArrayList<Client> clients) {

		this.bank = bank;
		this.viewRenderer = viewRenderer;
		this.clients = clients;
		this.time = new Time();
	}
	/**
	 * This method simulates the passing of one month.
	 * It notifies the model to perform monthly actions
	 */
	public void addMonths(){
		//register all the observers, that are affected by the time change
		time.registerObserver(bank);
		
		for (Client client : this.clients) {
			time.registerObserver(client);
		}
		
		while (true) {
			//the time object keeps track of the passed time and notifies the observers
			time.passOneMonth();
			//draw the output
			viewRenderer.render("Month: " + time.getMonthPassed());
			
			viewRenderer.render(bank.toString());
			
			for (Client client : clients) {
				viewRenderer.render(client.toString());
			}
			
			try {
				//make the demo sleep for a second to improve visibility
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
