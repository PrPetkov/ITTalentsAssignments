package model;

import java.util.*;
import java.util.Map.Entry;

import exceptions.InsufficientProductsException;
import exceptions.MusicShopException;
import interfaces.IObserver;

public class Shop implements IObserver{

	private String name;
	private double money;
	private Supplier suplier;
	
	private HashMap<String, HashMap<String, Double>> productCatalog;//Type -> Name -> Price
	private HashMap<String, HashMap<String, Queue<MusicInstrument>>> soldProducts;// Type -> Name -> products
	private HashMap<String, HashMap<String, Queue<MusicInstrument>>> products;// Type -> Name -> products

	public Shop(String name, double money, Supplier suplier) {
		this.name = name;
		this.money = money;
		this.suplier = suplier;
		
		this.products = new HashMap<>();
		this.productCatalog = new HashMap<>();
		this.soldProducts = new HashMap<>();
		
		this.initializeProductCatalog();
	}
		
	public String getName() {
		return name;
	}

	public double getMoney() {
		return money;
	}

	public synchronized void buyCustomOrderedInstrument(String instrument, int count) throws MusicShopException{
		try {
			this.suplier.deliverMusicInstrument(instrument, count);
		} catch (MusicShopException e) {
			throw new MusicShopException("This product can not be ordered", e);
		}
	}
	
	public synchronized int getCustomOrderExecutionTime(String instrument, int count) throws MusicShopException{
		try {
			return this.suplier.calculateTimeToDeliver(instrument, count);
		} catch (MusicShopException e) {
			throw new MusicShopException("This product can not be ordered", e);
		}
	}
	
	public synchronized ArrayList<Queue<MusicInstrument>> getSoldProductsBySells(){
		ArrayList<Queue<MusicInstrument>> productsBySells = new ArrayList<>();
		
		for (Entry<String, HashMap<String, Queue<MusicInstrument>>> type : this.soldProducts.entrySet()) {
			for (Entry<String, Queue<MusicInstrument>> musicInstruments : type.getValue().entrySet()) {
				productsBySells.add(musicInstruments.getValue());
			}
		}
		
		productsBySells.sort((o1, o2) -> {
			return ((Integer) o1.size()).compareTo(o2.size());
		});
		
		return productsBySells;
	}
	
	public synchronized double getTotalRevenue(){
		double totalrevenue = 0;
		
		for (Entry<String, HashMap<String, Queue<MusicInstrument>>> type : this.soldProducts.entrySet()) {
			for (Entry<String, Queue<MusicInstrument>> musicInstruments : type.getValue().entrySet()) {
				for (MusicInstrument instrument : musicInstruments.getValue()) {
					totalrevenue += instrument.getPrice();
				}
			}
		}
		
		return totalrevenue;
	}
	
	public synchronized MusicInstrument getMostSoldProduct(){
		MusicInstrument bestProduct = null;
		int bestSales = 0;
		
		for (Entry<String, HashMap<String, Queue<MusicInstrument>>> type : this.soldProducts.entrySet()) {
			for (Entry<String, Queue<MusicInstrument>> musicInstruments : type.getValue().entrySet()) {
				if (musicInstruments.getValue().size() > bestSales) {
					bestSales = musicInstruments.getValue().size();
					if (musicInstruments.getValue().size() > 0) {
						bestProduct = musicInstruments.getValue().peek();
					}
				}
			}
		}
		
		return bestProduct;
	}
	
	public synchronized String getMostSoldCategory(){
		String bestCategory = null;
		int mostSales = 0;
		
		for (Entry<String, HashMap<String, Queue<MusicInstrument>>> type : this.soldProducts.entrySet()) {
			int candidateMostSales = 0;
			
			for (Entry<String, Queue<MusicInstrument>> instruments : type.getValue().entrySet()) {
				candidateMostSales += instruments.getValue().size();
			}
			
			if (candidateMostSales > mostSales) {
				bestCategory = type.getKey();
				mostSales = candidateMostSales;
			}
		}
		
		return bestCategory;
	}
	
	public synchronized String getMostProfitableCategory(){
		String bestCategory = null;
		double bestSales = 0;
		
		for (Entry<String, HashMap<String, Queue<MusicInstrument>>> type : this.soldProducts.entrySet()) {
			double candidateBestSales = 0;
			
			for (Entry<String, Queue<MusicInstrument>> instruments : type.getValue().entrySet()) {
				Iterator<MusicInstrument> it = instruments.getValue().iterator();
				for (int i = 0; i < instruments.getValue().size(); i++) {
					candidateBestSales += it.next().getPrice();
				}
			}
			
			if (candidateBestSales > bestSales) {
				bestCategory = type.getKey();
				bestSales = candidateBestSales;
			}
		}
		
		return bestCategory;
	}
	
	public synchronized MusicInstrument getLeastSoldProduct(){
		MusicInstrument worstProduct = null;
		int worstSales = Integer.MAX_VALUE;
		
		for (Entry<String, HashMap<String, Queue<MusicInstrument>>> type : this.soldProducts.entrySet()) {
			for (Entry<String, Queue<MusicInstrument>> musicInstruments : type.getValue().entrySet()) {
				if (musicInstruments.getValue().size() < worstSales) {
					worstSales = musicInstruments.getValue().size();
					if (musicInstruments.getValue().size() > 0) {
						worstProduct = musicInstruments.getValue().peek();
					}
				}
			}
		}
		
		return worstProduct;
	}
	
	public synchronized HashMap<String, HashMap<String, Double>> getCatalogByType(){
		return this.productCatalog;
	}
	
	private synchronized ArrayList<Map.Entry<String, Double>> getproductCatalogAsList(){
		ArrayList<Map.Entry<String, Double>> productsList = new ArrayList<>();
		
		for (Entry<String, HashMap<String, Double>> entry : this.productCatalog.entrySet()) {
			productsList.addAll(entry.getValue().entrySet());
		}
		
		return productsList;
	}
	
	public synchronized ArrayList<Map.Entry<String, Double>> getProductsByNamesAlphabeticaly(){
		ArrayList<Map.Entry<String, Double>> sortedProducts = this.getproductCatalogAsList();
		
		sortedProducts.sort((o1, o2) -> {
			return o1.getKey().compareTo(o2.getKey());
		});
		
		return sortedProducts;	
	}
	
	public synchronized ArrayList<Map.Entry<String, Double>> getProductsByPrice(boolean sortDescending){
		ArrayList<Map.Entry<String, Double>> sortedProducts = this.getproductCatalogAsList();
		
		sortedProducts.sort((o1, o2) -> {
			int comparison = o1.getValue().compareTo(o2.getValue());
			
			return sortDescending ? - comparison : comparison;
		});
		
		return sortedProducts;
	}
	
	public synchronized ArrayList<MusicInstrument> getInstrumentsOnStock(){
		ArrayList<MusicInstrument> instrumentsOnStock = new ArrayList<>();
		
		for (Map.Entry<String, HashMap<String, Queue<MusicInstrument>>> type : this.products.entrySet()) {
			for (Map.Entry<String, Queue<MusicInstrument>> musicInstruments : type.getValue().entrySet()) {
				if (musicInstruments.getValue().size() > 0) {
					instrumentsOnStock.add(musicInstruments.getValue().peek());
				}
			}
		}
		
		return instrumentsOnStock;
	}
	
	public synchronized void supply(String suppliedInstrument, int suppliedCount) throws MusicShopException{		
		for (Map.Entry<String, HashMap<String, Double>> pair : this.productCatalog.entrySet()) {
			if (pair.getValue().containsKey(suppliedInstrument)) {
				String type = pair.getKey();
				
				if (!this.products.containsKey(type)) {
					this.products.put(type, new HashMap<>());
					this.soldProducts.put(type, new HashMap<>());
				}
				
				if (!this.products.get(type).containsKey(suppliedInstrument)){
					this.products.get(type).put(suppliedInstrument, new LinkedList<>());
					this.soldProducts.get(type).put(suppliedInstrument, new LinkedList<>());
				}
				
				for (int i = 0; i < suppliedCount; i++) {
					this.products.get(type).get(suppliedInstrument).offer(new MusicInstrument(
							suppliedInstrument, this.productCatalog.get(type).get(suppliedInstrument)));
				}

				return;
			}
		}
		
		throw new MusicShopException("the shop does not supports instument of type " + suppliedInstrument);
	}
	
	public synchronized ArrayList<MusicInstrument> sell(String requestedInstrument, int requestedCount) throws MusicShopException{
		ArrayList<MusicInstrument> productsToSell = new ArrayList<>();
		double price = 0;
		
		for (Map.Entry<String, HashMap<String, Queue<MusicInstrument>>> category : this.products.entrySet()) {
			if (category.getValue().containsKey(requestedInstrument)) {				
				if (category.getValue().get(requestedInstrument).size() < requestedCount) {
					throw new InsufficientProductsException(requestedCount + " " + requestedInstrument + 
							" were requested, but we only have " + category.getValue().get(requestedInstrument).size());
				}
				
				for (int i = 0; i < requestedCount; i++) {
					MusicInstrument soldInstrument = category.getValue().get(requestedInstrument).remove();
					price += soldInstrument.getPrice();
					productsToSell.add(soldInstrument);
					this.soldProducts.get(category.getKey()).get(requestedInstrument).offer(soldInstrument);
				}

				this.money += price;
				return productsToSell;
			}
		}
		
		throw new MusicShopException("the shop does not supports instument of type " + requestedInstrument);
	}
	
	private void initializeProductCatalog(){
		this.productCatalog.put("Strings", new HashMap<>());
		this.productCatalog.put("Percussion", new HashMap<>());
		this.productCatalog.put("Brass", new HashMap<>());
		this.productCatalog.put("Keyboards", new HashMap<>());
		this.productCatalog.put("Electronic", new HashMap<>());
		
		this.productCatalog.get("Strings").put("Violin", 700.0);
		this.productCatalog.get("Strings").put("Viola", 600.0);
		this.productCatalog.get("Strings").put("Guitar", 500.0);
		
		this.productCatalog.get("Percussion").put("Drum", 200.0);
		this.productCatalog.get("Percussion").put("Drums", 400.0);
		this.productCatalog.get("Percussion").put("Tambourine", 50.0);
		
		this.productCatalog.get("Brass").put("Trumpet", 300.0);
		this.productCatalog.get("Brass").put("Trombone", 450.0);
		this.productCatalog.get("Brass").put("Tuba", 1000.0);
		
		this.productCatalog.get("Keyboards").put("Accordion", 75.0);
		this.productCatalog.get("Keyboards").put("Piano", 3000.0);
		this.productCatalog.get("Keyboards").put("Organ", 10000.0);
		
		this.productCatalog.get("Electronic").put("Synthesizer", 2500.0);
		this.productCatalog.get("Electronic").put("Bass guitar", 950.0);
		this.productCatalog.get("Electronic").put("Electronic violin", 1050.0);		
	}
	
	private synchronized void orderSoldOutStock(){
		final int COUNT = 10;
		
		for (Entry<String, HashMap<String, Double>> type : this.productCatalog.entrySet()) {
			if (!this.products.containsKey(type.getKey())) {
				this.products.put(type.getKey(), new HashMap<>());
			}
			
			for (Entry<String, Double> instruments : type.getValue().entrySet()) {
				if (!this.products.get(type.getKey()).containsKey(instruments.getKey())) {
					this.products.get(type.getKey()).put(instruments.getKey(), new LinkedList<>());
				}
				
				if (this.products.get(type.getKey()).get(instruments.getKey()).size() == 0) {
					try {
						final int count = 10;
						
						System.out.println(this.name + " Waiting for products");
						this.buyCustomOrderedInstrument(instruments.getKey(), count);
						this.supply(instruments.getKey(), COUNT);
						System.out.println(this.name + "just bought " + count + " " + instruments.getKey());
					} catch (MusicShopException e) {
						System.out.println(this.name + " Eror occured during product buing: " + e.getMessage());
					}
				}
			}
		}
	}

	@Override
	public void update() {
		this.orderSoldOutStock();
	}
	
}
