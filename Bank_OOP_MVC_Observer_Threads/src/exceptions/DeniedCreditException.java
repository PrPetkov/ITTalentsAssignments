package exceptions;

public class DeniedCreditException extends BankException{
	
	public DeniedCreditException(){
		super();
	}
	
	public DeniedCreditException(String message){
		super(message);
	}
	
	public DeniedCreditException(Throwable couse){
		super(couse);
	}

	public DeniedCreditException(String message, Throwable couse) {
		super(message, couse);
	}
}
