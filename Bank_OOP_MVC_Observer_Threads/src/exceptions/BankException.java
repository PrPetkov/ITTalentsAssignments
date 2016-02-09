package exceptions;

public class BankException extends Exception{
	
	public BankException(){
		super();
	}
	
	public BankException(String message){
		super(message);
	}
	
	public BankException(Throwable couse){
		super(couse);
	}

	public BankException(String message, Throwable couse) {
		super(message, couse);
	}
}
