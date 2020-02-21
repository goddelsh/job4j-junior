package io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalizyTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void unavailable() throws Exception {
        Analizy analizy = new Analizy();
        File serverLog = temporaryFolder.newFile("server.log");
        File serverDownLog = temporaryFolder.newFile("serverdown_periods.log");


        try (PrintStream pstrean = new PrintStream(serverLog)) {
            var serverResponse = List.of("200 10:56:01",
                    "",
            "200 10:57:01",
                    "",
            "400 10:58:01",
                    "",
            "200 10:59:01",
                    "",
            "500 11:01:02",
                    "",
            "200 11:02:02");
            serverResponse.stream().forEach(pstrean::println);
        }
        analizy.unavailable(serverLog.getPath(), serverDownLog.getPath());
        try (BufferedReader read = new BufferedReader(new FileReader(serverDownLog))) {
            assertThat(read.readLine(), is("10:58:01;10:59:01;"));
            assertThat(read.readLine(), is("11:01:02;11:02:02;"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}