package interfaces;

public interface IObservedSubject {

	void registerObserver(IObserver observer);
	
	void unRegisterObserver(IObserver observer);
	
	void notifyObservers(String message);
}
