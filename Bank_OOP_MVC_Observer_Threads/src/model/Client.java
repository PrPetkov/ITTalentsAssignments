package model;

import java.util.*;

import exceptions.DeniedCreditException;
import exceptions.DepositException;
import interfaces.IObservedSubject;
import interfaces.IObserver;
import model.Bank.CreditType;
import model.Bank.DepositType;

public class Client implements IObserver, IObservedSubject{

	private String name;
	private String address;
	private double keshMoney;
	private double salary;
	
	private ArrayList<Deposit> deposits;
	private ArrayList<Credit> credits;
	private ArrayList<IObserver> observers;
	
	public Client(String name, String address, double keshMoney, double salary) {
		this.name = name;
		this.address = address;
		this.keshMoney = keshMoney;
		this.salary = salary;
		
		this.deposits = new ArrayList<>();
		this.credits = new ArrayList<>();
		this.observers = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public double getKeshMoney() {
		return keshMoney;
	}
	
	public void makeDeposit(DepositType type, double ammount, Bank bank){
		if (this.keshMoney <= ammount) {
			return;
		}
		
		try {
			Deposit deposit = bank.makeDeposit(type, ammount, this);
			this.deposits.add(deposit);
			this.keshMoney -= deposit.getAmmount();
		} catch (DepositException e) {
			this.notifyObservers(e.getMessage());
		}
	}
	
	public void applyForCredit(CreditType type, int months, double ammount, Bank bank){
		try {
			Credit credit = bank.processCreditApplication(type, this, ammount, months);
			this.credits.add(credit);
			
			this.keshMoney += credit.getAmmount();
		} catch (DeniedCreditException e) {
			this.notifyObservers(e.getMessage());
		}
	}
	
	/**
	 * Iterates over credits and pays the monthly payments
	 */
	public void payMonthlyPayments(){
		for (Credit credit : credits) {
			credit.getBank().takeCreditPayment(credit.getMonthlyPayment());
			this.keshMoney -= credit.getMonthlyPayment();
			
			credit.processMonth();
		}
	}
	
	public void takeMonthlyRevenue(double revenue){
		this.keshMoney += revenue;
	}
	
	/**
	 * Calculates the ratio of the monthly payments to the salary
	 * @param newPayment the payment for the credit that the client applies for
	 * @return the debt to salary ratio
	 */
	public double takeDebtToSalaryRatio(double newPayment){
		double totalPayments = newPayment;
		
		for (Credit credit : credits) {
			totalPayments += credit.getMonthlyPayment();
		}
		
		return totalPayments / this.salary;
	}
	
	private void recieveSalary(){
		this.keshMoney += this.salary;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
				
		builder.append("Client name: ");
		builder.append(this.name);
		builder.append(System.lineSeparator());
		builder.append("Address: ");
		builder.append(this.address);
		builder.append(System.lineSeparator());
		builder.append("Money ballance: ");
		builder.append(this.keshMoney);
		builder.append(System.lineSeparator());
		builder.append("Bank products: ");
		builder.append(System.lineSeparator());
		for (Credit credit : this.credits) {
			builder.append(credit.toString());
			builder.append(System.lineSeparator());
		}
		
		for (Deposit deposit : this.deposits) {
			builder.append(deposit.toString());
			builder.append(System.lineSeparator());
		}
		
		builder.append("Salary :");
		builder.append(this.salary);
		builder.append(System.lineSeparator());
		
		double totalPay = 0;
		for (Credit credit : credits) {
			totalPay += credit.getMonthlyPayment();
		}
		
		builder.append("Credits : ");
		builder.append(totalPay);
		builder.append(System.lineSeparator());
		
		return builder.toString();
	}
	
	/**
	 * Pay the monthly payments for the credits
	 */
	@Override
	public void update(String message) {
		this.recieveSalary();

		this.payMonthlyPayments();
		
		for (int i = 0; i < this.credits.size(); i++) {
			if(this.credits.get(i).getPeriodMonths() == 0 || this.credits.get(i).getAmmount() <= 0){
				this.credits.remove(this.credits.get(i));
				i--;
			}
		}
	}
	
	@Override
	public void registerObserver(IObserver observer) {
		this.observers.add(observer);		
	}

	@Override
	public void unRegisterObserver(IObserver observer) {
		this.observers.remove(observer);		
	}

	@Override
	public void notifyObservers(String message) {
		for (IObserver observer : this.observers) {
			if (observer != null) {
				observer.update(message);	
			}
		}		
	}
}
