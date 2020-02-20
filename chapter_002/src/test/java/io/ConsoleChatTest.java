package io;

import io.utils.Input;
import io.utils.Output;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConsoleChatTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void consoleChatWithAnswers() throws Exception {
        var file = temporaryFolder.newFile("answers.txt");
        var log = temporaryFolder.newFile("log.txt");
        FileWriter fw = new FileWriter(file);

        fw.write("Who\n");
        fw.write("Why\n");
        fw.write("Where\n");
        fw.write("When\n");
        fw.close();
        TestInput input = new TestInput();
        TestOutput output = new TestOutput();
        input.setPhrase(List.of("1", "стоп", "2", "продолжить", "3", "закончить", "4"));
        new ConsoleChat(file.getPath(), log.getPath(), input, output).init();


        assertThat(output.getLine().size(), is(2));

        try (BufferedReader logResult = new BufferedReader(new FileReader(log))) {
            var result = logResult.lines().collect(Collectors.toList());
            assertThat(result.size(), is(8));
        }


    }

    public class TestInput implements Input {

        private List<String> phrase;
        private int index = 0;

        public void setPhrase(List<String> phrase) {
            this.phrase = phrase;
        }

        @Override
        public String readInput() {
            String result = "закончить";
            if (index < phrase.size()) {
                result = this.phrase.get(index++);
            }
            return result;
        }
    }

    public class TestOutput implements Output {

        private List<String> printedLine = new ArrayList<>();

        public List<String>  getLine() {
            return this.printedLine;
        }

        @Override
        public void printLine(String line) {
            this.printedLine.add(line);
        }
    }
}