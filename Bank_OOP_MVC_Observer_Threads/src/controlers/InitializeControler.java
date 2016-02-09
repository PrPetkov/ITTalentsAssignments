package controlers;

import java.util.ArrayList;
import java.util.Random;

import interfaces.*;
import model.*;
import model.Bank.CreditType;
import model.Bank.DepositType;
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
		//runs the clients actions as different threads
		for (int i = 1; i <= 10; i++) {
			Client client = new Client("Client " + i, "Moon", 1000 + (100 * i), 500 + (200 * i));
			client.registerObserver(this);
			this.clients.add(client);
			
			Thread thread = new Thread(() -> {
				Random rnd = new Random();
				
				int depositPercentage = rnd.nextInt(19) + 80;
				
				if (rnd.nextBoolean()) {
					client.makeDeposit(DepositType.Short, (depositPercentage * client.getKeshMoney()) / 100, this.bank);
				}else {
					client.makeDeposit(DepositType.Long, (depositPercentage * client.getKeshMoney()) / 100, this.bank);
				}
				
				if (rnd.nextBoolean()) {
					client.applyForCredit(CreditType.Consumer, rnd.nextInt(108) + 12,rnd.nextInt(39_000) + 1_000, bank);
				}else {
					client.applyForCredit(CreditType.Home, rnd.nextInt(240) + 36,rnd.nextInt(200_000) + 10_000, bank);
				}
			});
			
			this.threads.add(thread);
			thread.start(); 
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
