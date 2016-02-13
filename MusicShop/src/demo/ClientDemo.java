package demo;

import java.util.ArrayList;
import java.util.Random;

import exceptions.InsufficientProductsException;
import exceptions.MusicShopException;
import model.Shop;
import model.Supplier;
import model.Time;

public class ClientDemo {

	public static void main(String[] args) {
		
		Time time = new Time();
		Shop shop = new Shop("Fender", 1000, new Supplier());
		
		ArrayList<Thread> clients = new ArrayList<>();
		
		time.registerObserver(shop);
		
		loadShop(shop);
		
		for (int i = 0; i < 2; i++) {
			Thread client = new Thread(() -> {			
				buySomeInstruments(shop, "Guitar", 10);
				buySomeInstruments(shop, "Drum", 5);
				buySomeInstruments(shop, "Tuba", 15);
				buySomeInstruments(shop, "Piano", 1);
				buySomeInstruments(shop, "Potato", 100);
			});
			
			clients.add(client);
			client.start();
		}
		
		clients.forEach((c) -> {
			try {
				c.join();
			} catch (Exception e) {
				e.printStackTrace();
			}});

		while (true) {
			System.out.println("Month: " + time.getMonthsPassed());
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			time.passMonth();
		}
	}
	
	private static void buySomeInstruments(Shop shop, String product, int count){
		Random rnd = new Random();
		
		try {
			shop.sell(product, count);
			System.out.println("Client " + Thread.currentThread().getName() + "Just bought " + count + " " + product);
		}catch(InsufficientProductsException e){
			System.out.println(e.getMessage());
			
			try {
				int timeToDeliver = shop.getCustomOrderExecutionTime(product, count);
				
				if (rnd.nextBoolean()) {
					System.out.println("Client " + Thread.currentThread().getName() + "I will wait for" + timeToDeliver + 
							" hours to compleate the order");
					System.out.println("Client " + Thread.currentThread().getName() + "Waiting...");
					
					shop.buyCustomOrderedInstrument(product, count);
					
					System.out.println("Client " + Thread.currentThread().getName() + "Just bought " + count + " " + product);
				}else {
					System.out.println("Client " + Thread.currentThread().getName() + "I will not wait for" + timeToDeliver +
							" hours to compleate the order");
				}
				
			} catch (MusicShopException e1) {
				System.out.println(e1.getMessage());
			}
		}catch (MusicShopException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	private static void loadShop(Shop shop){
		try {
			shop.supply("Piano", 5);
		} catch (MusicShopException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			shop.supply("Tuba", 2);
		} catch (MusicShopException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			shop.supply("Drum", 6);
		} catch (MusicShopException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			shop.supply("Guitar", 10);
		} catch (MusicShopException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			shop.supply("Potato", 100);
		} catch (MusicShopException e) {
			System.out.println(e.getMessage());
		}
	}

}
