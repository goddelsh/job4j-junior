package ru.job4j.analize;

import org.junit.Test;
import ru.job4j.analizr.Analize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizeTest {

    @Test
    public void diff() {
        Analize analize = new Analize();
        List<Analize.User> priv = new ArrayList<>(Arrays.asList(
                new Analize.User(6, "A"),
                new Analize.User(2, "F"),
                new Analize.User(5, "S"),
                new Analize.User(11, "C"),
                new Analize.User(1, "B")
                ));

        List<Analize.User> cur = new ArrayList<>(Arrays.asList(
                new Analize.User(100, "AAA"),
                new Analize.User(2, "FFF"),
                new Analize.User(3, "SSS")
        ));

        Analize.Info info = analize.diff(priv, cur);
        assertThat(info.getDeleted(), is(4));
        assertThat(info.getChanged(), is(1));
        assertThat(info.getAdded(), is(2));
    }
}