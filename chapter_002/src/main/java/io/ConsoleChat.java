package io;

import io.utils.Input;
import io.utils.Output;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {

    private final String stop = "стоп";
    private final String cont = "продолжить";
    private final String exit = "закончить";

    private Input reader;
    private Output writer;
    private File logFile;


    private List<String> dictionary;
    private final Random rand = new Random();

    public ConsoleChat(String dictionaryPath, String logPath, Input reader, Output writer) {
        dictionary = dictionaryInit(dictionaryPath);
        this.reader = reader;
        this.writer = writer;
        logFile = new File(logPath);
    }

    private List<String>  dictionaryInit(String dictionaryPath) {
        List<String> result = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(dictionaryPath))) {
            read.lines().forEach(line -> result.add(line));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void init() {
        var input = "";
        var isAnswerHim = true;
        try (PrintStream printer = new PrintStream(this.logFile)) {
            while (!exit.equals(input)) {
                input = reader.readInput();
                printer.println(input);
                if (cont.equals(input)) {
                    isAnswerHim = true;
                    continue;
                } else if (stop.equals(input)) {
                    isAnswerHim = false;
                    continue;
                }
                if (isAnswerHim && !exit.equals(input)) {
                    printer.println(answerHim());
                }
            }
            System.out.print(logFile);
        } catch (Exception e) {
            System.out.print(e.getLocalizedMessage());
        }

    }


    private String answerHim() {
        var index = rand.nextInt(dictionary.size());
        this.writer.printLine(dictionary.get(index));
        return dictionary.get(index);
    }

    public class ScannerInput implements Input {
        private Scanner reader = new Scanner(System.in);
        @Override
        public String readInput() {
            return reader.nextLine();
        }
    }

    public class ScannerOutput implements Output {

        @Override
        public void printLine(String line) {
            System.out.println(line);
        }
    }

}
