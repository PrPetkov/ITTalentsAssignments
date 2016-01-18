
public class Vokalist extends Musician {

	public Vokalist(String name) {
		super(name);

	}

	@Override
	public void play(Song song) {
		super.play(song);
		System.out.println(song.getText());
		System.out.println("Yeah!");
		
	}

}
