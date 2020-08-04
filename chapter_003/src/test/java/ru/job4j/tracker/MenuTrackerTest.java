package ru.job4j.tracker;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class MenuTrackerTest {

    @Test
    public void whenExit() {
        StubInput input = new StubInput(
                new String[] {"0"}
        );
        StubAction action = new StubAction(0, "Input zero");
        MenuTracker menuTracker = new MenuTracker(input, new SqlTracker(), System.out::println);
        menuTracker.startExecute(action);
        assertThat(action.isCall(), is(true));
    }

    @Test
    public void whenPrtMenu() {
        StringBuilder stringBuilded = new StringBuilder("");
        Consumer<String> output = str -> stringBuilded.append(str);
        StubInput input = new StubInput(
                new String[] {"0"}
        );
        StubAction action = new StubAction(0, "Stub action");
        MenuTracker menuTracker = new MenuTracker(input, new SqlTracker(), output);
        menuTracker.setActions(Arrays.asList(action));
        menuTracker.showMenu();
        menuTracker.startExecute(action);
        String expect = new StringJoiner("")
                .add("Menu.")
                .add("0 : Stub action")
                .toString();
        assertThat(stringBuilded.toString(), is(expect));
    }
}
