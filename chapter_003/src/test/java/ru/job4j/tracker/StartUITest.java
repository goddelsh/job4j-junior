package ru.job4j.tracker;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {
    @Test
    public void whenExit() {
        StringJoiner result = new StringJoiner(System.lineSeparator());
        Consumer<String> output = outStr -> result.add(outStr).toString();
                StubInput input = new StubInput(
                new String[] {"6"}
        );
        new StartUI(input, new SqlTracker(), output).init();
        String expected = new StringJoiner(System.lineSeparator())
                .add("Menu.")
                .add("0 : create item")
                .add("1 : print all items")
                .add("2 : edit item")
                .add("3 : delete item")
                .add("4 : find item by id")
                .add("5 : find item by name")
                .add("6 : exit")
                .toString();
        assertThat(result.toString(), is(expected));
    }
}