package view;


import interfaces.IOutputBuilder;

public class ConsoleWriter implements IOutputBuilder {
    @Override
    public void writeLine(String text) {
        System.out.println(text);
    }
}
