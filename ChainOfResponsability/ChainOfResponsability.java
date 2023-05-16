package ChainOfResponsability;

import java.util.*;

interface ProcessChar {
    void next(ProcessChar processor);
    void process(char character);
    void getCount();
}

class ProcessSpace implements ProcessChar {
    private int count = 0;
    private ProcessChar nextProcessor;

    public void next(ProcessChar processor) {
        nextProcessor = processor;
    }

    public void process(char character) {
        if (character == ' ') {
            count++;
        }
        if (nextProcessor != null) {
            nextProcessor.process(character);
        }
    }

    public void getCount() {
        System.out.println("Spaces: " + count);
        nextProcessor.getCount();
    }
}

class ProcessA implements ProcessChar{
    private int count = 0;
    private ProcessChar nextProcessor;

    public void next(ProcessChar processor) {
        nextProcessor = processor;
    }

    public void process(char character) {
        if (character == 'a' || character == 'A') {
            count++;
        }
        if (nextProcessor != null) {
            nextProcessor.process(character);
        }
    }

    public void getCount() {
        System.out.println("A: " + count);
        nextProcessor.getCount();
    }
}

class ProcessDot implements ProcessChar{
    private int count = 0;
    private ProcessChar nextProcessor;

    public void next(ProcessChar processor) {
        nextProcessor = processor;
    }

    public void process(char character) {
        if (character == '.') {
            count++;
        }
        if (nextProcessor != null) {
            nextProcessor.process(character);
        }
    }

    public void getCount() {
        System.out.println("Dots: " + count);
        nextProcessor.getCount();
    }
}

class ProcessNull implements ProcessChar{
    public void next(ProcessChar processor) {
        return;
    }

    public void process(char character) {
        return;
    }

    public void getCount() {
        return;
    }
}
class Statistcs {
    private ProcessChar processChar;

    public Statistcs (){
        processChar = new ProcessSpace();
        ProcessChar processA = new ProcessA();
        ProcessChar processDot = new ProcessDot();
        ProcessChar last = new ProcessNull();

        processChar.next(processA);
        processA.next(processDot);
        processDot.next(last);
    }

    public void processInput(String input){
        for (char c : input.toCharArray()){
            processChar.process(c);
        }
        processChar.getCount();
    }
}

class Main {
    public static void main(String[] args) {
        Statistcs statistcs = new Statistcs();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Input: ");
            String input = scanner.nextLine();

            statistcs.processInput(input);
        }
    }
}

