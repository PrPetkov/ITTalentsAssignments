package model;

public class Credit extends Account{

	private double monthlyPayment;
	
	public Credit(String name, int periodMonths, double revenue, double ammount, Client client, Bank bank) {
		super(name, periodMonths, revenue, ammount, client, bank);
		this.setMontlyPayment();
	}

	public double getMonthlyPayment() {
		return this.monthlyPayment;
	}
	
	private void setMontlyPayment(){
		//the formula is not the focus of the practice, so i'm using sample formula
			double monthRevenue = super.getRevenue() / 12;
			double totalRevenue = super.getAmmount() * monthRevenue * super.getPeriodMonths();
			double payment = (super.getAmmount() + totalRevenue) / super.getPeriodMonths();
			
			this.monthlyPayment = payment;
	}
	
	@Override
	public String toString() {
		return "Credit: " + System.lineSeparator() + super.toString();
	}

	@Override
	public void processMonth() {
		super.setPeriodMonths(super.getPeriodMonths() - 1);
		
		double monthRevenue = (super.getRevenue() / 12) * super.getAmmount();
		double paiedOffAmmount = this.getMonthlyPayment() - (monthRevenue);
		
		super.setAmmount(getAmmount() - paiedOffAmmount);
	}
}
