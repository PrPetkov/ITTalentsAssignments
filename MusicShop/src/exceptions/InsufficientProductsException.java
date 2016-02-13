package exceptions;

public class InsufficientProductsException extends MusicShopException{
	
	public InsufficientProductsException() {
		super();
	}
	
	public InsufficientProductsException(String message){
		super(message);
	}
	
	public InsufficientProductsException(Throwable couse){
		super(couse);
	}
	
	public InsufficientProductsException(String message, Throwable couse){
		super(message, couse);
	}

}
