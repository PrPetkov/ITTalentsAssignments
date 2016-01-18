
public class FestivalDemo {

	public static void main(String[] args) {
		
		Group guns = new Group("Guns n' roses", 
				new Musician[] {new Vokalist("Axl"), new Drumer("Mat"),
						new BassGuitarist("Izzy"), new Guitarist("Slash")},
				new Song[]{new Song("Don't cry", "I still love you, babe"),
						new Song("Paradise city", "Take me down to the paradise city")});
		
		Act mainStage = new Act("22.30", "24.00", guns);
		
		mainStage.perform(5);

	}

}
