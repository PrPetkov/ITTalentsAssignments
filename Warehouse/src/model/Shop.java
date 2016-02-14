package model;

import java.util.HashMap;
import java.util.Random;
import java.util.Map.Entry;

public class Shop implements Runnable{
	
	private static final int DELIVERED_PRODUCTS_COUNT = 5;
	private static final int MIN_STOCK = 5;
	private static final int INITIAL_PRODUCT_QUANTITY = 0;
	
	private HashMap<String, HashMap<String, Integer>> products;
	
	private Warehouse warehouse;
	private String name;
	
	public Shop(Warehouse warehouse, String name) {
		this.warehouse = warehouse;
		this.name = name;
		this.products = new HashMap<>();
		this.initializeStartingProducts();
	}
	
	@Override
	public void run() {
//		Random rnd = new Random();
		
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			this.supply();
//			String boughtProduct = Warehouse.PRODUCT_NAMES.get(rnd.nextInt(Warehouse.PRODUCT_NAMES.size())); 
//			
//			this.warehouse.sell(boughtProduct);
//			
//			System.out.println(this.name + " just bought " + boughtProduct);	
		}
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
	}
	
	public synchronized void supply(){
		for (Entry<String, HashMap<String, Integer>> type : this.products.entrySet()) {
			for (Entry<String, Integer> product : type.getValue().entrySet()) {
				if (product.getValue() < MIN_STOCK) {
					String suppliedProduct = product.getKey();
					this.warehouse.sell(suppliedProduct);
					this.products.get(type.getKey()).put(suppliedProduct, product.getValue() + DELIVERED_PRODUCTS_COUNT);
					
					System.out.println(this.name + " just supplied " + suppliedProduct);
					
					notifyAll();
				}
			}
		}
		
//		try {
//			wait();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
	
	public synchronized void sell(String product){
		Random rnd = new Random();
		int boughtProducts = rnd.nextInt(4) + 1;
		
		for (Entry<String, HashMap<String, Integer>> type : this.products.entrySet()) {
			if (type.getValue().containsKey(product)) {
				if (type.getValue().get(product) < boughtProducts) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else {
					type.getValue().put(product, type.getValue().get(product) - boughtProducts);
					
					notifyAll();
					
					System.out.println(this.name + " : " + type.getValue().get(product) + " " + product + " left");
					
//					if (type.getValue().get(product) < Shop.MIN_STOCK) {
//						this.supply();
//					}
				}
			}
		}
	}
}
