package interfaces;

public interface IObservedObject {

	void registerObserver(IObserver observer);
	
	void unregisterObserver(IObserver observer);
	
	void notifyObservers();
}
