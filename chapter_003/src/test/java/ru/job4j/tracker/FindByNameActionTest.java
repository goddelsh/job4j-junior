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

public class FindByNameActionTest {

    public Connection init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")


            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void addTwoNamesAndCheck() throws SQLException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        Store tracker = new SqlTracker(ConnectionRollback.create(this.init()));
        Item item1 = new Item("Tom");
        tracker.add(item1);
        Item item2 = new Item("Tom");
        tracker.add(item2);
        FindByNameAction findByname = new FindByNameAction(4, "find item by name");
        findByname.execute(new StubInput(new String[]{"Tom", ""}), tracker);
        String expect = new StringJoiner(System.lineSeparator())
                .add("Enter name: ")
                .add("0. id: " + item1.getId() + " name: " + item1.getName())
                .add("1. id: " + item2.getId() + " name: " + item2.getName())
                .add("")
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
        System.setOut(def);
    }

    @Test
    public void executeMock() throws SQLException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        Store tracker = new SqlTracker(ConnectionRollback.create(this.init()));
        Item item1 = new Item("Tom");
        tracker.add(item1);
        Item item2 = new Item("Tom");
        tracker.add(item2);


        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn("Tom");

        FindByNameAction findByname = new FindByNameAction(4, "find item by name");
        findByname.execute(input, tracker);

        String expect = new StringJoiner(System.lineSeparator())
                .add("Enter name: ")
                .add("0. id: " + item1.getId() + " name: " + item1.getName())
                .add("1. id: " + item2.getId() + " name: " + item2.getName())
                .add("")
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
        System.setOut(def);
    }
}
