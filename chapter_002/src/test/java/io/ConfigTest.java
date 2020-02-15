package io;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        Config config = new Config("./data/whithout_comments.properties");
        config.load();
        assertThat(config.value("age"), is("33"));
    }

    @Test
    public void whenPairWithComment() {
        Config config = new Config("./data/app.properties");
        config.load();
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
    }


}