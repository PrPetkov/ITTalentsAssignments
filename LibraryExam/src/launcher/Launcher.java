package launcher;


import controler.LibraryEngine;
import interfaces.IOutputBuilder;
import view.ConsoleWriter;

public class Launcher {

    public static void main(String[] args) {

        IOutputBuilder writer= new ConsoleWriter();

        LibraryEngine engine = new LibraryEngine(writer);

        engine.run();
    }
}
