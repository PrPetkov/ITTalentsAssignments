package model;

import java.util.Random;

public class Client implements Runnable{

	Warehouse warehouse;
		
	public Client(Warehouse warehouse) {
		this.warehouse = warehouse;
	}
	
	@Override
	public void run() {
		Random rnd = new Random();
		String boughtProduct = Warehouse.PRODUCT_NAMES.get(rnd.nextInt(Warehouse.PRODUCT_NAMES.size())); 
		
		this.warehouse.sell(boughtProduct);
		
		System.out.println(Thread.currentThread().getName() + " just bought " + boughtProduct);
	}

}
