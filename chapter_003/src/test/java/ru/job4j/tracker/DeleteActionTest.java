package ru.job4j.tracker;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteActionTest {

    @Test
    public void testDeleteWithMock() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        Store tracker = new MemTracker();
        Item item1 = new Item("Tom");
        tracker.add(item1);
        Item item2 = new Item("Karl");
        tracker.add(item2);


        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn("1");

        DeleteAction deletAaction = new DeleteAction(4, "find item by id");
        deletAaction.execute(input, tracker);

        String expect = new StringJoiner(System.lineSeparator())
                .add("Enter id: ")
                .add("item edited successfuly")
                .add("")
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
        System.setOut(def);
    }
}