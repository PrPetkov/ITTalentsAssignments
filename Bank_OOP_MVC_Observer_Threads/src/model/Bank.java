package model;

import java.util.ArrayList;

import interfaces.IObservedSubject;
import interfaces.IObserver;

public class Bank implements IObservedSubject, IObserver{

	public enum CreditType{Home, Consumer}
	public enum DepositType{Short, Long}
	
	private String name;
	private String address;
	private double monney;
	private double minReservs;
	
	private ArrayList<IObserver> observers;
	private ArrayList<Account> accounts;

	public Bank(String name, String address, double monney) {
		this.name = name;
		this.address = address;
		this.setMonney(monney);
		
		this.observers = new ArrayList<>();
		this.accounts = new ArrayList<>();
	}
	
	private void setMonney(double monney) {
		if (monney > 0) {
			this.monney = monney;
		}
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public double getMonney() {
		return monney;
	}

	public double getMinReservs() {
		return minReservs;
	}
	/**
	 * Makes deposit, when requested
	 * @param type The type of the deposit according to the bank conditions
	 * @param ammount The amount of money of the deposit
	 * @param client The client, that requests the deposit
	 * @return If the parameters are correct returns the new deposit, else returns null
	 */
	public synchronized Deposit makeDeposit(DepositType type, double ammount, Client client){
		if (ammount <= 0) {
			return null;
		}
		
		Deposit deposit = null;
		
		switch (type) {
		case Short:
			deposit = new Deposit("ShortTerm", 3, 0.03, ammount, client, this);
			break;
		case Long:
			deposit = new Deposit("LongTerm", 12, 0.05, ammount, client, this);
		default:
			break;
		}
		
		if (deposit != null) {
			this.accounts.add(deposit);
			this.setMonney(this.monney + deposit.getAmmount());
			this.minReservs += 0.1 * deposit.getAmmount();
			//Notify the observers about the new product
			this.notifyObservers("A deposit for" + deposit.getAmmount() + ", was made, Bank ballance: " + this.monney + 
					" minimal reserves: " + this.minReservs);
			
			return deposit;
		}
		
		return null;
	}
	/**
	 * Evaluates credit application according to the bank policy
	 * @param type The type of the credit according to the bank conditions
	 * @param client The client, that requests the credit
	 * @param ammount The amount of money of the credit
	 * @param months The period of the credit
	 * @return returns the credit if the request is approved or null if the request is declined
	 */
	public synchronized Credit processCreditApplication(CreditType type, Client client, double ammount, int months){
		if (ammount <= 0 || months <= 0) {
			return null;
		}
		
		if ((this.monney - ammount) < this.minReservs) {
			return null;
		}
		
		Credit credit = null;
		
		switch (type) {
		case Home:
			credit = new Credit("Mortgate", months, 0.06, ammount, client, this);
			break;
		case Consumer:
			credit = new Credit("Consumer", months, 0.1, ammount, client, this);
			break;
		default:
			break;
		}
		
		if (credit != null && client.takeDebtToSalaryRatio(credit.getMonthlyPayment()) <= 0.5) {
			this.accounts.add(credit);
			this.monney -= credit.getAmmount();
			//Notify the observers about the new product
			this.notifyObservers("A credit was given, " + credit.getAmmount() + "lv, for " + 
			credit.getPeriodMonths() + "months, Bank ballance: " + this.monney);
			
			return credit;
		}
		
		return null;
	}
	/**
	 * Iterates the bank products and pays revenue to deposit owners.
	 * If the deposit is matured restarts it.
	 */
	public synchronized void payDepositRevenue(){
		for (Account account : accounts) {
			if (account instanceof Deposit) {
				((Deposit)account).getClient().takeMonthlyRevenue(((Deposit)account).getMounthRevenue());
				this.monney -= ((Deposit)account).getMounthRevenue();
				
				account.processMonth();
				if (account.getPeriodMonths() == 0) {
					((Deposit) account).restartDeposit();
				}
				
			}
		}
	}
	/**
	 * Takes monthly payments from customers
	 * @param ammount the monthly payment
	 */
	public synchronized void takeCreditPayment(double ammount){
		if (ammount > 0) {
			this.setMonney(this.monney + ammount);
		}
	}

	@Override
	public synchronized void registerObserver(IObserver observer) {
		this.observers.add(observer);		
	}

	@Override
	public synchronized void unRegisterObserver(IObserver observer) {
		this.observers.remove(observer);		
	}

	@Override
	public synchronized void notifyObservers(String message) {
		for (IObserver observer : this.observers) {
			if (observer != null) {
				observer.update(message);	
			}
		}		
	}
	
	@Override
	public synchronized String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("Bank name: ");
		builder.append(this.name);
		builder.append(System.lineSeparator());
		builder.append("Address: ");
		builder.append(this.address);
		builder.append(System.lineSeparator());
		builder.append("Ballance: ");
		builder.append(this.monney);
		builder.append(System.lineSeparator());
		builder.append("Minimal reserves: ");
		builder.append(this.minReservs);
		builder.append(System.lineSeparator());
		builder.append("Bank products count: ");
		builder.append(this.accounts.size());
		
		return builder.toString();
	}
	/**
	 * Pay deposit revenue
	 */
	@Override
	public synchronized void update(String message) {
		this.payDepositRevenue();	
		
		for (int i = 0; i < this.accounts.size(); i++) {
			if (!(this.accounts.get(i) instanceof Credit)) {
				continue;
			}
			
			if(this.accounts.get(i).getPeriodMonths() == 0 || this.accounts.get(i).getAmmount() <= 0){
				this.accounts.remove(this.accounts.get(i));
				i--;
			}
		}
	}
}
