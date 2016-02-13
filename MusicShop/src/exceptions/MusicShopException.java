package exceptions;

public class MusicShopException extends Exception{

	public MusicShopException() {
		super();
	}
	
	public MusicShopException(String message){
		super(message);
	}
	
	public MusicShopException(Throwable couse){
		super(couse);
	}
	
	public MusicShopException(String message, Throwable couse){
		super(message, couse);
	}
}
