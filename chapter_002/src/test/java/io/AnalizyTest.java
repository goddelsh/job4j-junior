package io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalizyTest {

    @Test
    public void unavailable() {
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/server.log", "./data/serverdown_periods.log");
        try (BufferedReader read = new BufferedReader(new FileReader("./data/serverdown_periods.log"))) {
            assertThat(read.readLine(), is("10:58:01;10:59:01;"));
            assertThat(read.readLine(), is("11:01:02;11:02:02;"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}