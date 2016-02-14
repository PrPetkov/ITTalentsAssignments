package demo;

import java.util.ArrayList;
import java.util.Random;

import model.Shop;
import model.Warehouse;

public class Demo {

	public static void main(String[] args) {
		final Warehouse warehouse = new Warehouse("Warehouse");
		
		Shop kaufland = new Shop(warehouse, "Kaufland");
		Shop fantastiko = new Shop(warehouse, "Fantastiko");
		Shop billa = new Shop(warehouse, "Billa");
		
		Thread kauflandThread = new Thread(kaufland);
		Thread fantastikoThread = new Thread(fantastiko);
		Thread billaThread = new Thread(billa);
		
		Thread supplier = new Thread(() -> {
			while (true) {
				warehouse.supply();	
			}
		});
		
		ArrayList<Thread> custumers = new ArrayList<>();
		Random rnd = new Random();
		
		for (int i = 0; i < 3; i++) {
			custumers.add(new Thread(() -> {
				while (true) {
					try {
						Thread.sleep(5000);
						System.out.println("------------------------");
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					String product = Warehouse.PRODUCT_NAMES.get(rnd.nextInt(Warehouse.PRODUCT_NAMES.size()));
					
					kaufland.sell(product);
					
					System.out.println(Thread.currentThread().getName() + " bought " + product );
				}
			}));
			
			custumers.add(new Thread(() -> {
				while (true) {
					try {
						Thread.sleep(5000);
						System.out.println("------------------------");
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					String product = Warehouse.PRODUCT_NAMES.get(rnd.nextInt(Warehouse.PRODUCT_NAMES.size()));
					
					fantastiko.sell(product);
					
					System.out.println(Thread.currentThread().getName() + " bought " + product );
				}
			}));
			
			custumers.add(new Thread(() -> {
				while (true) {
					try {
						Thread.sleep(5000);
						System.out.println("------------------------");
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					String product = Warehouse.PRODUCT_NAMES.get(rnd.nextInt(Warehouse.PRODUCT_NAMES.size()));
					
					billa.sell(product);
					
					System.out.println(Thread.currentThread().getName() + " bought " + product );
				}
			}));
		}

		custumers.forEach((x) -> {x.start();});
		
		kauflandThread.start();
		fantastikoThread.start();
		billaThread.start();
		
		supplier.start();
	}

}
