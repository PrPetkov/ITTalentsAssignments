
public class Drumer extends Musician {

	public Drumer(String name) {
		super(name);
		
	}

	@Override
	public void play(Song song) {
		super.play(song);
		System.out.println("Boom boom boom");
		
	}

}
