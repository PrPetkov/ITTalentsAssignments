package controler;


import exceptions.LibraryException;
import interfaces.IOutputBuilder;
import model.Library;
import model.TakeableEntry;
import model.TextBook;
import view.CustomFileWriter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LibraryEngine {

    private IOutputBuilder writer;
    Library library;

    public LibraryEngine(IOutputBuilder writer) {
        this.writer = writer;
        this.library = new Library("National library");
    }

    public void run(){
        Thread bookThread = new Thread(() -> {
            Random rnd = new Random();

            TextBook textBook = new TextBook("Java for everyone", "Unknown", "Programing", "Krasi");
            TakeableEntry takenEntry = null;

            try {
                takenEntry = LibraryEngine.this.library.takeLibraryEntrie(textBook, "Pesho");
                writer.writeLine("Pesho just took a book, " + takenEntry.getName());
            } catch (LibraryException e) {
                LibraryEngine.this.writer.writeLine(e.getMessage());
            }

            if (takenEntry == null){
                return;
            }

            try {
                Thread.sleep(textBook.getMaxLendTime() * 100);
            } catch (InterruptedException e) {
                writer.writeLine(e.getMessage());
            }

            if (rnd.nextBoolean()){

                writer.writeLine("Retuning the book");
                writer.writeLine("Loan fee: " + takenEntry.getLoanFee());

                library.returnLibraryEntry(takenEntry);
                return;
            }

            while (takenEntry.isTaken()){
                if (rnd.nextBoolean()){

                    writer.writeLine("Retuning the book");
                    writer.writeLine("Loan fee: " + takenEntry.getLoanFee());

                    library.returnLibraryEntry(takenEntry);
                    return;
                }
            }
        });

        Thread revisionThread = new Thread(() -> {
           while (true){
               HashMap<String, String> reports = LibraryEngine.this.library.makeRevision();

               for (Map.Entry<String, String> entry : reports.entrySet()){
                   CustomFileWriter fWriter = new CustomFileWriter(entry.getKey() + " year " + LocalDateTime.now().getYear() +
                           " seconds " + LocalDateTime.now().getSecond() + ".txt");
                   fWriter.writeLine(entry.getValue());
               }

               try {
                   Thread.sleep(5000);
               } catch (InterruptedException e) {
                   LibraryEngine.this.writer.writeLine(e.getMessage());
               }
           }
        });

        this.writer.writeLine("Welcome to " + this.library.getName());

        bookThread.start();
        revisionThread.start();
    }
}
