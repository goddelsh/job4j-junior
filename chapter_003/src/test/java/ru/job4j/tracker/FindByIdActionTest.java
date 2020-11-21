package ru.job4j.tracker;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindByIdActionTest {

    @Test
    public void testFindByIdWithMock() {
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

        FindByIdAction findByIdAction = new FindByIdAction(4, "find item by id");
        findByIdAction.execute(input, tracker);

        String expect = new StringJoiner(System.lineSeparator())
                .add("Enter id: ")
                .add("Founded. id: " + item2.getId() + " name: " + item2.getName())
                .add("")
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
        System.setOut(def);
    }


}