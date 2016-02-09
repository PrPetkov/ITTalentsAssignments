package demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		Thread tire = new Thread(() -> {
			try {
				Thread.sleep(2000);
				System.out.println("a tyre was made by: " + Thread.currentThread().getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		Thread seat = new Thread(() -> {
			try {
				Thread.sleep(3000);
				System.out.println("a seat was made by: " + Thread.currentThread().getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		Thread engine = new Thread(() -> {
			try {
				Thread.sleep(7000);
				System.out.println("engine was made by: " + Thread.currentThread().getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		Thread frame = new Thread(() -> {
			try {
				Thread.sleep(5000);
				System.out.println("frame was made by: " + Thread.currentThread().getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		long startTime = System.currentTimeMillis();
		
		executor.execute(engine);
		executor.execute(frame);
		for (int i = 0; i < 5; i++) {
			executor.execute(seat);
		}
		
		for (int i = 0; i < 4; i++) {
			executor.execute(tire);
		}
		
		executor.shutdown();
		// waits for production threads to finish work
		while (!executor.isTerminated()){}
		
		System.out.println("All done for: " + (System.currentTimeMillis() - startTime));	
	}

}
