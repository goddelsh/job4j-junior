package io;

import io.utils.Input;
import io.utils.Output;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {

    private final String stop = "стоп";
    private final String cont = "продолжить";
    private final String exit = "закончить";

    private Input reader;
    private Output printer;

    private List<String> dictionary = new ArrayList<>();
    private final Random rand = new Random();

    public ConsoleChat(String dictionaryPath, Input reader, Output printer) {
        dictionaryInit(dictionaryPath);
        this.reader = reader;
        this.printer = printer;
    }

    private void dictionaryInit(String dictionaryPath) {
        try (BufferedReader read = new BufferedReader(new FileReader(dictionaryPath))) {
            read.lines().forEach(dictionary::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {
        var input = "";
        var isAnswerHim = true;
        while (!input.equals(exit)) {
            input = reader.readInput();
            if (input.equals(cont)) {
                isAnswerHim = true;
                continue;
            } else if (input.equals(stop)) {
                isAnswerHim = false;
                continue;
            }
            if (isAnswerHim && !input.equals(exit)) {
                answerHim();
            }
        }
    }

    private void answerHim() {
        var index = rand.nextInt(dictionary.size());
        this.printer.printLine(dictionary.get(index));
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
