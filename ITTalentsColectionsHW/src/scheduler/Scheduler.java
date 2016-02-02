package scheduler;

import java.util.*;

public final class Scheduler {

	private Queue<ITask> tasks;
	private static Scheduler singleScheduler = null;
	//Current logic does not requires more than one instance
	//training singleton
	private Scheduler() {
		this.tasks = new LinkedList<>();
	}
	
	public static Scheduler getScheduler(){
		if (Scheduler.singleScheduler == null) {
			Scheduler.singleScheduler = new Scheduler();
		}
		
		return Scheduler.singleScheduler;
	}

	public void pushTask(ITask task) throws SchedulerException{
		if (task == null) {
			//protect the collection from pollution with garbage data
			throw new SchedulerException("Scheduler can not push null task");
		}
		
		this.tasks.offer(task);
	}
	
	public void executeNextTask() throws SchedulerException{
		try {
			this.tasks.remove().doWork();;
			
		} catch (NoSuchElementException e) {
			//catch the private logic exception and throw appropriate one
			throw new SchedulerException("The Scheduler is empty!");
		}
	}
	
}
