package model;

import java.time.LocalDateTime;
import java.util.*;
import java.util.Map.Entry;

import exceptions.LibraryException;
import interfaces.Takeable;

public class Library {
	
	private String name;
	private ArrayList<Takeable> takenEntryes;
	private HashMap<String, TreeMap<String, TreeSet<LibraryEntry>>> libraryEntries;
	//Category -> type -> entries
	public Library(String name) {

		this.name = name;
		this.initializeLibraryEntries();
		this.takenEntryes = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	
	public synchronized void returnLibraryEntry(TakeableEntry returnedBook){
		double totalDebt = returnedBook.getTotalDebt();
		this.takenEntryes.remove(returnedBook);
		returnedBook.returnBook();
	}
	
	public synchronized HashMap<String, String> makeRevision(){
		int countOfBooks = 0;
		StringBuilder builder = new StringBuilder();
        HashMap<String, String> reports = new HashMap<>(); //report name -> report
		
		for (Entry<String, TreeMap<String, TreeSet<LibraryEntry>>> category : this.libraryEntries.entrySet()) {
			for (Entry<String, TreeSet<LibraryEntry>> type : category.getValue().entrySet()) {
				countOfBooks += type.getValue().size();
			}
		}
		
//		CustomFileWriter writer = new CustomFileWriter("revision " + LocalDateTime.now().toString() + ".txt");
		
		builder.append("Total number of taken things ").append(this.takenEntryes.size());
        builder.append(System.lineSeparator());
		builder.append("Taken library entries: ");
        builder.append(System.lineSeparator());
		
		this.takenEntryes.forEach((x) -> {
            builder.append(x.getType()).append(" ").append(x.getName());
            builder.append(System.lineSeparator());
        });

        reports.put("revision", builder.toString());

        builder.setLength(0);
        builder.trimToSize();

//		CustomFileWriter debtWriter = new CustomFileWriter("Debts " + LocalDateTime.now() + ".txt");

        builder.append("Debts : ");
        builder.append(System.lineSeparator());
		
		ArrayList<Takeable> delays = new ArrayList<>();
		
		this.takenEntryes.forEach((x) -> {if (x.getTax() > 0) {
			delays.add(x);
		}});
		
		delays.sort((o1, o2) -> ((Double) o1.getTax()).compareTo(o2.getTax()));
		
		delays.forEach((x) -> {
            builder.append(x.getName()).append(" ").append(x.getTax());
            builder.append(System.lineSeparator());
			});

        reports.put("debts", builder.toString());

        return reports;
	}
	
	public synchronized TakeableEntry takeLibraryEntrie(Takeable paper, String takerName) throws LibraryException{
		String classType = paper.getClass().getSimpleName();
		String type = paper.getType();
		String name = paper.getName();

		Iterator<LibraryEntry> iterator = this.libraryEntries.get(classType).get(type).iterator();
		
		while (iterator.hasNext()) {
			LibraryEntry currentEntry = iterator.next();
			
			if (currentEntry.getName().equals(name)) {
				if (((Takeable)currentEntry).isTaken()) {
					throw new LibraryException("Already taken");
				}
				
				((TakeableEntry) currentEntry).take(takerName);
				this.takenEntryes.add((Takeable)currentEntry);

                return (TakeableEntry) currentEntry;
			}
		}
        return null;
    }

	public TreeMap<String, TreeSet<LibraryEntry>> getCatalogByType(String type) throws LibraryException{
		switch (type) {
		case "Book":
			
		case "Magazine":
			
		case "TextBook":
			return this.libraryEntries.get(type);

		default:
			throw new LibraryException("No such category");
		}
	}
	
	private synchronized void initializeLibraryEntries(){
		this.libraryEntries = new HashMap<>();
		
		this.libraryEntries.put("Book", new TreeMap<>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		}));
		this.libraryEntries.put("Magazine", new TreeMap<>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		}));
		this.libraryEntries.put("TextBook", new TreeMap<>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		}));
		
		this.libraryEntries.get("Book").put("Novel", new TreeSet<>(new Comparator<LibraryEntry>() {

			@Override
			public int compare(LibraryEntry o1, LibraryEntry o2) {
				if (o1 instanceof Book && o2 instanceof Book) {
					return ((Book) o1).getMadeOn().compareTo(((Book) o2).getMadeOn());
				} else {
					throw new IllegalArgumentException("The element must be of type book");
				}
			}
		}));
		
		this.libraryEntries.get("Book").put("Thriler", new TreeSet<>(new Comparator<LibraryEntry>() {

			@Override
			public int compare(LibraryEntry o1, LibraryEntry o2) {
				if (o1 instanceof Book && o2 instanceof Book) {
					return ((Book) o1).getMadeOn().compareTo(((Book) o2).getMadeOn());
				} else {
					throw new IllegalArgumentException("The element must be of type book");
				}
			}
		}));
		
		this.libraryEntries.get("Magazine").put("Fashion", new TreeSet<>(new Comparator<LibraryEntry>() {

			@Override
			public int compare(LibraryEntry o1, LibraryEntry o2) {
				if (o1 instanceof Magazine && o2 instanceof Magazine) {
					if (((Magazine) o1).getName().compareTo(((Magazine) o2).getName()) == 0) {
						return ((Integer)((Magazine) o1).getNumber()).compareTo(((Magazine) o2).getNumber());
					}
					
					return ((Magazine) o1).getName().compareTo(((Magazine) o2).getName());
				} else {
					throw new IllegalArgumentException("The element must be of type magazine");
				}
			}
		}));
		
		this.libraryEntries.get("Magazine").put("Scintific", new TreeSet<>(new Comparator<LibraryEntry>() {

			@Override
			public int compare(LibraryEntry o1, LibraryEntry o2) {
				if (o1 instanceof Magazine && o2 instanceof Magazine) {
					if (((Magazine) o1).getName().compareTo(((Magazine) o2).getName()) == 0) {
						return ((Integer)((Magazine) o1).getNumber()).compareTo(((Magazine) o2).getNumber());
					}
					
					return ((Magazine) o1).getName().compareTo(((Magazine) o2).getName());
				} else {
					throw new IllegalArgumentException("The element must be of type magazine");
				}
			}
		}));
		
		this.libraryEntries.get("TextBook").put("History", new TreeSet<>(new Comparator<LibraryEntry>() {

			@Override
			public int compare(LibraryEntry o1, LibraryEntry o2) {
				return o1.getName().compareTo(o2.getName());
			}
		}));
		
		this.libraryEntries.get("TextBook").put("Programing", new TreeSet<>(new Comparator<LibraryEntry>() {

			@Override
			public int compare(LibraryEntry o1, LibraryEntry o2) {
				return o1.getName().compareTo(o2.getName());
			}
		}));
		
		this.libraryEntries.get("Book").get("Novel").add(new Book("For whom the bell rings", "Ciela", "Novel", "Heminguey", LocalDateTime.now()));
		this.libraryEntries.get("Book").get("Novel").add(new Book("Green hills of Africa", "Ciela", "Novel", "Heminguey", LocalDateTime.now()));
		
		this.libraryEntries.get("Book").get("Thriler").add(new Book("Baskerville dog", "Ciela", "Thriler", "UnKnown", LocalDateTime.now()));
		this.libraryEntries.get("Book").get("Thriler").add(new Book("Castle", "Ciela", "Thriler", "Unknown", LocalDateTime.now()));
		
		this.libraryEntries.get("Magazine").get("Fashion").add(new Magazine("Vanity fair", "who knows?", "Fashion", 1, LocalDateTime.now()));
		this.libraryEntries.get("Magazine").get("Fashion").add(new Magazine("Vanity fair", "who knows?", "Fashion", 2, LocalDateTime.now()));
		
		this.libraryEntries.get("Magazine").get("Scintific").add(new Magazine("NatGeo", "who knows?", "Scintific", 1, LocalDateTime.now()));
		this.libraryEntries.get("Magazine").get("Scintific").add(new Magazine("NatGeo", "who knows?", "Scintific", 2, LocalDateTime.now()));
		
		this.libraryEntries.get("TextBook").get("History").add(new TextBook("History 8 grade", "Prosveta", "History", "B.Dimitrov"));
		this.libraryEntries.get("TextBook").get("History").add(new TextBook("History 9 grade", "Prosveta", "History", "B.Dimitrov"));
		
		this.libraryEntries.get("TextBook").get("Programing").add(new TextBook("Java for everyone", "Unknown", "Programing", "Krasi"));
		this.libraryEntries.get("TextBook").get("Programing").add(new TextBook("Just java", "Prosveta", "Programing", "Jenkov"));
	}
	
	
}
