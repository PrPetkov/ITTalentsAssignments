package exceptions;

public class DepositException extends BankException{
	
	public DepositException(){
		super();
	}
	
	public DepositException(String message){
		super(message);
	}
	
	public DepositException(Throwable couse){
		super(couse);
	}

	public DepositException(String message, Throwable couse) {
		super(message, couse);
	}
}
