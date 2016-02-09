package model;

public class Deposit extends Account{

	private double monthRevenue;
	private int originalPeriod;
	
	public Deposit(String name, int periodMonths, double revenue, double ammount, Client client, Bank bank) {
		super(name, periodMonths, revenue, ammount, client, bank);
		
		this.originalPeriod = periodMonths;
		this.setMonthRevenue();
	}
	
	public double getMounthRevenue(){
		return this.monthRevenue;
	}
	
	private void setMonthRevenue(){
		double mounthRevenue = super.getRevenue() / super.getPeriodMonths();
		
		this.monthRevenue = super.getAmmount() * mounthRevenue;
	}
	
	public void restartDeposit() {
		super.setPeriodMonths(this.originalPeriod);		
	}
	
	@Override
	public String toString() {
		return "Deposit: " + System.lineSeparator() + super.toString();
	}

	@Override
	public void processMonth() {
		super.setPeriodMonths(super.getPeriodMonths() - 1); 		
	}
}
