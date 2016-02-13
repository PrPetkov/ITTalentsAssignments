package model;

import java.util.ArrayList;

import interfaces.IObservedObject;
import interfaces.IObserver;

public class Time implements IObservedObject {
	
	private int monthsPassed;
	
	private ArrayList<IObserver> observers;
		
	public Time() {

		this.observers = new ArrayList<>();
	}

	public int getMonthsPassed() {
		return monthsPassed;
	}

	public void passMonth(){
		this.monthsPassed += 1;
		
		this.notifyObservers();
	}
	
	@Override
	public void registerObserver(IObserver observer) {
		if (observer != null) {
			this.observers.add(observer);
		}
	}

	@Override
	public void unregisterObserver(IObserver observer) {
		this.observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for (IObserver observer : observers) {
			observer.update();
		}
	}

}
