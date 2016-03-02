package com.example.lifeorganiser.src.Models.user;

import com.example.lifeorganiser.src.Models.accounts.Account;
import com.example.lifeorganiser.src.Models.events.DatedEvent;
import com.example.lifeorganiser.src.Models.events.Event;
import com.example.lifeorganiser.src.Models.events.PaymentEvent;
import com.example.lifeorganiser.src.Models.events.ShoppingList;
import com.example.lifeorganiser.src.Models.events.TODOEvent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class User {

	private String userName;
	private String password;
    private int uniqueDBId;
	private String email;

	private TreeSet<DatedEvent> events;
	private ArrayList<TODOEvent> todos;
	private ArrayList<ShoppingList> shoppingList;
	private ArrayList<Account> accounts;

	public User(String userName, String password, int uniqueDBId, String email) {
        this.setUserName(userName);
        this.setPassword(password);
        this.uniqueDBId = uniqueDBId;
        this.email = email;

		this.accounts = new ArrayList<>();
		this.todos = new ArrayList<>();
		this.shoppingList = new ArrayList<>();

        this.events = new TreeSet<DatedEvent>(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((DatedEvent)o1).getDateTime().compareTo(((DatedEvent)o2).getDateTime());
            }
        });
	}

	// methods
	
	/*-------------PAYMENT EVENT-------------*/
	void addPaymentEvent(PaymentEvent event) {
        this.events.add(event);
	}


	void removePaymentEvent(PaymentEvent event) {
		this.events.remove(event);
	}
	
	public void pay(PaymentEvent event) {
		//TODO should this be here?
	}
	
	/*------------TODO EVENT------------*/	
	//
	void addTODO(TODOEvent todo) {
        this.todos.add(todo);
	}

	void removeTODO(TODOEvent todo) {
		this.todos.remove(todo);
	}
	
	/*--------------SHOPPING LIST EVENT---------------*/

	void addShoppingList(ShoppingList shoppingList) {
        this.shoppingList.add(shoppingList);
	}

	void removeShoppingList(ShoppingList list) {
		this.shoppingList.remove(list);
	}

	public void payShoppingList() {
        //TODO should this be here?
	}
	/*------------------------------------*/
	
	// TODO
	/*
	 * SINGLETON MANAGER CLASS GETS USER private User(String userName) { this();
	 * setUserName(userName); setPassword(password); }
	 * 
	 * public static User getUser(){ if (User.user == null){ User.user = new
	 * User(); }
	 * 
	 * return User.user; }
	 * 
	 * public static boolean isValidUser(String username, String password) { if
	 * (username == null || password == null){ return false; }
	 * 
	 * //TODO check in the database for the user return true; }
	 * 
	 * public void downloadInfoFromDb(){ //TODO }
	 * 
	 * public void uploadInfoToDb(){ //TODO }
	 * 
	 */

	private void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	private void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return this.userName;
	}

	public TreeSet<DatedEvent> getEvents() {
		return events;
	}

	public Iterable<TODOEvent> getTodos() {
		return todos;
	}

	public void addEvent(DatedEvent event){
        if (event == null){
            return;
        }

        this.events.add(event);
    }

    public int getId(){
        return this.uniqueDBId;
    }

}
