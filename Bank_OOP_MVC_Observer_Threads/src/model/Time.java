package model;

import java.util.ArrayList;

import interfaces.IObservedSubject;
import interfaces.IObserver;

public class Time implements IObservedSubject{

	private int monthPassed;
	
	private ArrayList<IObserver> observers;
	
	public Time() {
		this.observers = new ArrayList<>();
	}
	
	public int getMonthPassed() {
		return monthPassed;
	}
	
	public void passOneMonth(){
		this.monthPassed++;
		this.notifyObservers("A month just passed!");
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
		for (IObserver observer : observers) {
			observer.update(message);
		}
	}

}
