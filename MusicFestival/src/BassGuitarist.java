
public class BassGuitarist extends Musician {

	public BassGuitarist(String name) {
		super(name);

	}

	@Override
	public void play(Song song) {
		super.play(song);
		System.out.println("playing the bass guitar");
		
	}

}
