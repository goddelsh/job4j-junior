package net;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.Socket;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OraculServerTest {

    @Test
    public void whenInputExit() throws Exception {
        this.init(
                new StringJoiner(System.lineSeparator()).add("exit").toString(),
                new StringJoiner(System.lineSeparator()).add("").toString()
        );
    }

    @Test
    public void whenSayHelloAndThenExit() throws Exception {
        this.init(
                new StringJoiner(System.lineSeparator()).add("hello").add("exit").toString(),
                new StringJoiner(System.lineSeparator())
                        .add("Hello, dear friend, I'm a oracle.")
                        .add("")
                        .add("")
                        .toString()
        );
    }

    @Test
    public void whenHelloThanCountingAndExit() throws Exception {
        this.init(
                new StringJoiner(System.lineSeparator())
                        .add("hello")
                        .add("counting")
                        .add("exit")
                        .toString(),
                new StringJoiner(System.lineSeparator())
                        .add("Hello, dear friend, I'm a oracle.")
                        .add("")
                        .add("Start counting:")
                        .add("1")
                        .add("2")
                        .add("")
                        .add("")
                        .toString()
        );
    }

    @Test
    public void whenInputUnknownCommandThanExit() throws Exception {
        this.init(
                new StringJoiner(System.lineSeparator())
                        .add("nothing")
                        .add("exit")
                        .toString(),
                new StringJoiner(System.lineSeparator())
                        .add("Unknown command")
                        .add("")
                        .add("")
                        .toString()
        );
    }

    public void init(String input, String expected) throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Socket mock = mock(Socket.class);
        when(mock.getInputStream()).thenReturn(in);
        when(mock.getOutputStream()).thenReturn(out);
        new OraculServer(mock).init();
        assertThat(out.toString(), is(expected));
    }
}