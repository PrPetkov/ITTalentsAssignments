
public class Act {

	private String startTime;
	private String endTime;
	private Group group;
	
	public Act(String startTime, String endTime, Group group) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.group = group;
	}
	
	public void perform(int numberOfSongs){
		
		System.out.println(this.startTime + ": Let the fun begin");
		this.group.perform(numberOfSongs);
		System.out.println(this.endTime + ": We had great time with you, goodbye");
	}
	
}
