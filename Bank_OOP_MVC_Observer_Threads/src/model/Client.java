package model;

import java.util.*;

import Interfaces.IObserver;
import model.Bank.CreditType;
import model.Bank.DepositType;

public class Client implements Runnable, IObserver{

	private String name;
	private String address;
	private double keshMoney;
	private double salary;
	private Bank bank;
	
	private ArrayList<Deposit> deposits;
	private ArrayList<Credit> credits;
	
	public Client(String name, String address, double keshMoney, double salary, Bank bank) {
		this.name = name;
		this.address = address;
		this.keshMoney = keshMoney;
		this.salary = salary;
		this.bank = bank;
		
		this.deposits = new ArrayList<>();
		this.credits = new ArrayList<>();
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
	
	@Override
	public void run() {	
		Random rnd = new Random();
		
		int depositPercentage = rnd.nextInt(19) + 80;
		
		if (rnd.nextBoolean()) {
			this.makeDeposit(DepositType.Short, (depositPercentage * this.keshMoney) / 100, this.bank);
		}else {
			this.makeDeposit(DepositType.Long, (depositPercentage * this.keshMoney) / 100, this.bank);
		}
		
		if (rnd.nextBoolean()) {
			this.applyForCredit(CreditType.Consumer, rnd.nextInt(108) + 12,rnd.nextInt(39_000) + 1_000, bank);
		}else {
			this.applyForCredit(CreditType.Home, rnd.nextInt(240) + 36,rnd.nextInt(200_000) + 10_000, bank);
		}
	}
	
	public void makeDeposit(DepositType type, double ammount, Bank bank){
		if (this.keshMoney <= ammount) {
			return;
		}
		
		Deposit deposit = bank.makeDeposit(type, ammount, this);
		
		if (deposit != null) {
			this.deposits.add(deposit);
			this.keshMoney -= deposit.getAmmount();
		}
	}
	
	public void applyForCredit(CreditType type, int months, double ammount, Bank bank){
		Credit credit = bank.processCreditApplication(type, this, ammount, months);
		
		if (credit != null) {
			this.credits.add(credit);
			
			this.keshMoney += credit.getAmmount();
		}
	}
	
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
	
	public double takeDeptToSalary(double newPayment){
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
}
