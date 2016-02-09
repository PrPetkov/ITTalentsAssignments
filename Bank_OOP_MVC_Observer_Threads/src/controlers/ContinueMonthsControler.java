package controlers;

import java.util.ArrayList;

import Interfaces.IView;
import model.Bank;
import model.Client;
import model.Time;

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
	
	public void addMonths(){
		time.registerObserver(bank);
		
		for (Client client : this.clients) {
			time.registerObserver(client);
		}
		
		while (true) {
			time.passOneMonth();
			
			viewRenderer.render("Month: " + time.getMonthPassed());
			
			viewRenderer.render(bank.toString());
			
			for (Client client : clients) {
				viewRenderer.render(client.toString());
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
