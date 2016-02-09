package controlers;

import java.util.ArrayList;

import Interfaces.IObserver;
import Interfaces.IView;
import model.Bank;
import model.Client;
import views.ConsoleView;

public class InitializeControler implements IObserver{

	Bank bank;
	ArrayList<Client> clients;
	ArrayList<Thread> threads;
	IView viewRenderer;
	
	public void initialize(){
		this.bank = new Bank("RBB", "Vapcarov 55", 500_000);
		this.bank.registerObserver(this);
		
		this.viewRenderer = new ConsoleView();
		
		this.threads = new ArrayList<>();
		this.clients = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			this.clients.add(new Client("Client " + i, "Moon", 1000 + (100 * i), 500 + (200 * i), this.bank));
			this.threads.add(new Thread(this.clients.get(i - 1)));
			this.threads.get(i - 1).start(); 
		}
		
		for (Thread thread : this.threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.viewRenderer.render(this.bank.toString());
		
		ContinueMonthsControler nextControler = new ContinueMonthsControler(bank, viewRenderer, clients);
		
		nextControler.addMonths();
	}

	@Override
	public void update(String message) {
		this.viewRenderer.render(message);
	}
	
}
