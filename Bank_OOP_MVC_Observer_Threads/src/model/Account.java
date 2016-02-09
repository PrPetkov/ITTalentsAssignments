package model;

public abstract class Account {
	
	private String name;
	private int periodMonths;
	private double revenue;
	private double ammount;
	private Client client;
	private Bank bank;
	
	public Account(String name, int periodMonths, double revenue, double ammount, Client client, Bank bank) {
		this.name = name;
		this.periodMonths = periodMonths;
		this.revenue = revenue;
		this.ammount = ammount;
		this.client = client;
		this.bank = bank;
	}
	
	protected void setAmmount(double ammount) {
		if (ammount >= 0) {
			this.ammount = ammount;
		}else {
			this.ammount = 0;
		}

	}

	protected void setPeriodMonths(int periodMonths) {
		this.periodMonths = periodMonths;
	}

	public Bank getBank() {
		return bank;
	}

	public Client getClient() {
		return client;
	}

	public String getName() {
		return name;
	}

	public int getPeriodMonths() {
		return periodMonths;
	}

	public double getRevenue() {
		return revenue;
	}

	public double getAmmount() {
		return ammount;
	}
	
	public abstract void processMonth();

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("Type: ");
		builder.append(this.name);
		builder.append(System.lineSeparator());
		builder.append("Bank: ");
		builder.append(this.bank.getName());
		builder.append(System.lineSeparator());
		builder.append("Ammount: ");
		builder.append(this.ammount);
		builder.append(System.lineSeparator());
		builder.append("Period left: ");
		builder.append(this.periodMonths);
		builder.append(" mounths.");
		
		return builder.toString();
	}
}
