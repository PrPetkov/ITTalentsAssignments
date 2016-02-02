package scheduler;

public class Demo {

	public static void main(String[] args) {
		
		Scheduler scheduler = Scheduler.getScheduler();

		try {
			scheduler.pushTask(() -> {System.out.println("Clean your desk");});
			scheduler.pushTask(() -> {System.out.println("Write some code");});
			scheduler.pushTask(() -> {System.out.println("Play some FIFA, you can't work all the time, right?");});
			scheduler.pushTask(() -> {System.out.println("Ask the boss for promotion!");});
			scheduler.pushTask(null);
		} catch (SchedulerException e) {
			System.out.println("Task not added, error: " + e.getMessage());
		}
		
		for (int i = 0; i < 5; i++) {
			try {
				scheduler.executeNextTask();
			} catch (SchedulerException e) {
				System.out.println("An error occured during task execution: " + e.getMessage());
			}
		}
		
	}

}
