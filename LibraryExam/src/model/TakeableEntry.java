package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import interfaces.Takeable;

public abstract class TakeableEntry extends LibraryEntry implements Takeable{
	
	protected String takenBy;
	protected double tax;
    protected boolean isTaken;
    private ArrayList<Lend> lends;

	public TakeableEntry(String name, String publisher, String type, boolean isTaken) {
		super(name, publisher, type);

        this.isTaken = isTaken;
        this.lends = new ArrayList<Lend>();
	}

	@Override
	public void take(String takenBy) {
		this.getLends().add(new Lend(LocalDateTime.now()));
		this.takenBy = takenBy;
		this.isTaken = true;
	}

	@Override
	public void returnBook() {
		this.tax = 0;
		this.takenBy = null;
		this.getLends().get(this.getLends().size() - 1).setReturnedOn(LocalDateTime.now());
	}

    @Override
    public void CheckForDelay() {
        if (!this.isTaken){
            return;
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (this.getLends().get(this.getLends().size() - 1).getTakenOn().plusNanos(this.getMaxLendTime() * 1000).isAfter(
                LocalDateTime.now())) {
            this.tax += this.getLoanFee() * this.getTaxProcentage();
        }

    }

    @Override
    public double getTotalDebt() {
        return this.getLoanFee() + this.tax;
    }

    public abstract int getMaxLendTime();

    public abstract double getTaxProcentage();

    public abstract double getLoanFee();

    public boolean isTaken() {
        return isTaken;
    }

	public double getTax() {
		return tax;
	}

    public ArrayList<Lend> getLends() {
        return lends;
    }

}
