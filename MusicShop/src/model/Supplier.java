package model;

import java.util.HashMap;
import java.util.Map.Entry;

import exceptions.MusicShopException;

public class Supplier {

	private HashMap<String, HashMap<String, Integer>> products;

	public Supplier() {
		
		this.initializeProducts();
	}
	
	public int calculateTimeToDeliver(String musicInstrument, int count) throws MusicShopException{
		for (Entry<String, HashMap<String, Integer>> type : this.products.entrySet()) {
			if (type.getValue().containsKey(musicInstrument)) {
				return count * type.getValue().get(musicInstrument);
			}
		}
		
		throw new MusicShopException("The suplier does not have instrument type " + musicInstrument);
	}
	
	public void deliverMusicInstrument(String musicInstrument, int count) throws MusicShopException{
		for (Entry<String, HashMap<String, Integer>> type : this.products.entrySet()) {
			if (type.getValue().containsKey(musicInstrument)) {
				try {
					Thread.sleep(count * type.getValue().get(musicInstrument) * 100);
					return;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		throw new MusicShopException("The suplier does not have instrument type " + musicInstrument);		
	}
	
	private void initializeProducts(){
		this.products = new HashMap<>();
		
		this.products.put("Strings", new HashMap<>());
		this.products.put("Percussion", new HashMap<>());
		this.products.put("Brass", new HashMap<>());
		this.products.put("Keyboards", new HashMap<>());
		this.products.put("Electronic", new HashMap<>());
		
		this.products.get("Strings").put("Violin", 3);
		this.products.get("Strings").put("Viola", 2);
		this.products.get("Strings").put("Guitar", 1);
		
		this.products.get("Percussion").put("Drum", 3);
		this.products.get("Percussion").put("Drums", 2);
		this.products.get("Percussion").put("Tambourine", 1);
		
		this.products.get("Brass").put("Trumpet", 1);
		this.products.get("Brass").put("Trombone", 4);
		this.products.get("Brass").put("Tuba", 5);
		
		this.products.get("Keyboards").put("Accordion", 1);
		this.products.get("Keyboards").put("Piano", 38);
		this.products.get("Keyboards").put("Organ", 10);
		
		this.products.get("Electronic").put("Synthesizer", 5);
		this.products.get("Electronic").put("Bass guitar", 3);
		this.products.get("Electronic").put("Electronic violin", 4);		
	}
	
}
