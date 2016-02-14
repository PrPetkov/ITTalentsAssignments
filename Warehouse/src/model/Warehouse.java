package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Warehouse {
	
	public static final ArrayList<String> PRODUCT_NAMES = new ArrayList<>(); 
	
	private static final int BOUGHT_PRODUCTS_COUNT = 5;
	private static final int DELIVERED_PRODUCTS_COUNT = 25;
	private static final int MIN_STOCK = 10;
	private static final int INITIAL_PRODUCT_QUANTITY = 15;
	
	private HashMap<String, HashMap<String, Integer>> products;
	private String name;

	public Warehouse(String name) {

		this.name = name;
		this.products = new HashMap<>();
		this.initializeStartingProducts();
	}
	
	private void initializeStartingProducts(){
		this.products.put("Meat", new HashMap<>());
		this.products.put("Fruit", new HashMap<>());
		this.products.put("Vegetables", new HashMap<>());
		
		this.products.get("Fruit").put("Banana", INITIAL_PRODUCT_QUANTITY);
		this.products.get("Fruit").put("Orange", INITIAL_PRODUCT_QUANTITY);
		this.products.get("Fruit").put("Apple", INITIAL_PRODUCT_QUANTITY);
		
		this.products.get("Vegetables").put("Potato", INITIAL_PRODUCT_QUANTITY);
		this.products.get("Vegetables").put("Eggplant", INITIAL_PRODUCT_QUANTITY);
		this.products.get("Vegetables").put("Cucumber", INITIAL_PRODUCT_QUANTITY);
		
		for (Entry<String, HashMap<String, Integer>> type : this.products.entrySet()) {
			Warehouse.PRODUCT_NAMES.addAll(type.getValue().keySet());
		}
	}
	
	public synchronized void supply(){
		for (Entry<String, HashMap<String, Integer>> type : this.products.entrySet()) {
			for (Entry<String, Integer> product : type.getValue().entrySet()) {
				if (product.getValue() < MIN_STOCK) {
					String suppliedProduct = product.getKey();
					this.products.get(type.getKey()).put(suppliedProduct, product.getValue() + DELIVERED_PRODUCTS_COUNT);
					
					System.out.println(this.name + " just supplied " + suppliedProduct);
					
					notifyAll();
				}
			}
		}
		
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void sell(String product){
		for (Entry<String, HashMap<String, Integer>> type : this.products.entrySet()) {
			if (type.getValue().containsKey(product)) {
				if (type.getValue().get(product) < BOUGHT_PRODUCTS_COUNT) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else {
					type.getValue().put(product, type.getValue().get(product) - BOUGHT_PRODUCTS_COUNT);
					
					System.out.println(this.name + " : " + type.getValue().get(product) + " " + product + " left");
					
					notifyAll();
				}
			}
		}
	}
}
