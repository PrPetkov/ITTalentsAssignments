package scheduler;

public class SchedulerException extends Throwable{

	public SchedulerException(){
		super();
	}
	
	public SchedulerException(String message, Throwable cause){
		super(message, cause);
	}
	
	public SchedulerException(String message){
		super(message);
	}

	public SchedulerException(Throwable cause){
		super(cause);
	}
}
