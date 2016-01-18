
public class Group {
	
	private static final int DEFAULT_MUSICIANS_COUNT = 4;
	private static final int DEFAULT_SONGS_COUNT = 4;
	
	private String name;
	private Musician[] musitians;
	private Song[] songs;
	private int freePlacesForMusicians;
	private int freePlacesForSongs;
	
	
	
	public Group(String name) {
		this(name, new Musician[Group.DEFAULT_MUSICIANS_COUNT], new Song[Group.DEFAULT_SONGS_COUNT]);

		this.freePlacesForMusicians = Group.DEFAULT_MUSICIANS_COUNT;
		this.freePlacesForSongs = Group.DEFAULT_SONGS_COUNT;
	}

	public Group(String name, Musician[] musitians, Song[] songs) {
		this.name = name;
		this.musitians = musitians;
		this.songs = songs;
	}
	
	public String getName() {
		return name;
	}

	public void addMusician(Musician newMusician){
		if (this.freePlacesForMusicians > 0){
			this.musitians[this.musitians.length - this.freePlacesForMusicians--] = newMusician;
		}else {
			System.out.println("Sorry, no free places for musicians");
		}
	}
	
	public void addSong(Song newSong){
		if (this.freePlacesForSongs > 0) {
			this.songs[this.songs.length - this.freePlacesForSongs--] = newSong;
		}else{
			System.out.println("Sorry, the musicians can not learn any more songs");
		}
	}
	
	public void perform(final int numberOfSongs){
		
		if(this.songs == null || this.songs.length == 0){
			System.out.println("Sorry, no songs");
			return;
		}
		
		for (int song = 0; song < songs.length && song < numberOfSongs; song++) {
			System.out.println("Song " + this.songs[song].getName());
			for (int perf = 0; perf < musitians.length; perf++) {
				this.musitians[perf].play(this.songs[song]);
			}	
		}
		
		if(this.songs.length < numberOfSongs){
			System.out.println("Sorry, no more songs");
		}
		
	}

}
