package model;

import java.time.LocalDateTime;

import interfaces.Takeable;

public class Book extends TakeableEntry{

	private static final int MAX_LEND_TIME = 300;
	private static final double TAX_PROCENTAGE = 0.01;
	private static final double LOAN_FEE = 2;
	
	private String author;
	private LocalDateTime madeOn;
	
	public Book(String name, String publisher, String type, String author, LocalDateTime madeOn) {
		super(name, publisher, type, false);
		
		this.author = author;
		this.madeOn = madeOn;
	}

	public String getAuthor() {
		return author;
	}

	public LocalDateTime getMadeOn() {
		return madeOn;
	}

	@Override
    public int getMaxLendTime() {
		return Book.MAX_LEND_TIME;
	}

	@Override
    public double getTaxProcentage() {
		return Book.TAX_PROCENTAGE;
	}

	@Override
    public double getLoanFee() {
		return Book.LOAN_FEE;
	}
	
}
