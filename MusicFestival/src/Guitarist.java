
public class Guitarist extends Musician{

	public Guitarist(String name) {
		super(name);

	}

	@Override
	public void play(Song song) {
		super.play(song);
		System.out.println("playing the guitar");
		
	}

}
