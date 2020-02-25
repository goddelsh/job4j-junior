package net;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OraculClientTest {

    @Test
    public void whenInputLongMessageThanExit() throws Exception {
        this.init(
                new StringJoiner(System.lineSeparator())
                        .add("counting")
                        .add("exit")
                        .add("")
                        .toString(),
                new StringJoiner(System.lineSeparator())
                        .add("1")
                        .add("2")
                        .add("3")
                        .add("")
                        .toString(),
                new StringJoiner(System.lineSeparator())
                        .add("1")
                        .add("2")
                        .add("3")
                        .add("")
                        .toString()
        );
    }


    private void init(String command, String input, String expected) throws Exception {
        ByteArrayInputStream inputCommand = new ByteArrayInputStream(command.getBytes());
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Socket mock = mock(Socket.class);
        when(mock.getOutputStream()).thenReturn(new ByteArrayOutputStream());
        when(mock.getInputStream()).thenReturn(in);
        new OraculClient(mock, inputCommand, new PrintStream(out)).init();
        assertThat(out.toString(), is(expected));
    }
}