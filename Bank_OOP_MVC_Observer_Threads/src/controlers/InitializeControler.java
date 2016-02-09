package controlers;

import java.util.ArrayList;

import interfaces.*;
import model.*;
import views.ConsoleView;

public class InitializeControler implements IObserver{

	Bank bank;
	ArrayList<Client> clients;
	ArrayList<Thread> threads;
	IView viewRenderer;
	/**
	 * Generates clients and a bank. Clients apply for bank products
	 */
	public void initialize(){
		//set up the resources
		this.bank = new Bank("RBB", "Vapcarov 55", 500_000);
		this.bank.registerObserver(this);
		
		this.viewRenderer = new ConsoleView();
		
		this.threads = new ArrayList<>();
		this.clients = new ArrayList<>();
		//adds 10 clients
		//runs the clients as different threads
		for (int i = 1; i <= 10; i++) {
			this.clients.add(new Client("Client " + i, "Moon", 1000 + (100 * i), 500 + (200 * i), this.bank));
			this.threads.add(new Thread(this.clients.get(i - 1)));
			this.threads.get(i - 1).start(); 
		}
		//joins the threads to sum the results from their execution
		for (Thread thread : this.threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.viewRenderer.render(this.bank.toString());
		//Calls the next state of the program
		ContinueMonthsControler nextControler = new ContinueMonthsControler(bank, viewRenderer, clients);
		
		nextControler.addMonths();
	}

	@Override
	public void update(String message) {
		//Listens for events from the bank
		//events can be creation of a bank product
		this.viewRenderer.render(message);
	}
	
}
