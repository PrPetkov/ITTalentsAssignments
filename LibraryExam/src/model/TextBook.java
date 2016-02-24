package model;

import java.time.LocalDateTime;

import interfaces.Takeable;

public class TextBook extends TakeableEntry {
	
	private static final int MAX_LEND_TIME = 150;
	private static final double TAX_PROCENTAGE = 0.01;
	private static final double LOAN_FEE = 3;

	private String author;
	
	public TextBook(String name, String publisher, String type, String author) {
		super(name, publisher, type, false);
		this.author = author;
	}

	public String getAuthor() {
		return author;
	}

	@Override
	public int getMaxLendTime() {
		return TextBook.MAX_LEND_TIME;
	}

	@Override
	public double getTaxProcentage() {
		return TextBook.TAX_PROCENTAGE;
	}

	@Override
	public double getLoanFee() {
		return TextBook.LOAN_FEE;
	}
}
