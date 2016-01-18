
public abstract class Musician {

	private String name;

	
	
	public Musician(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void play(Song song){
		System.out.print(this.name + ": ");
	}
	
}
