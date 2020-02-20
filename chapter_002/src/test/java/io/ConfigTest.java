package io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConfigTest {


    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void withOnlyComments() throws Exception {
        File file = temporaryFolder.newFile("log_with_comments.log");
        FileWriter fw = new FileWriter(file);

        fw.write("###value=1\n");
        fw.write("#test2=2\n");
        fw.write("\n");
        fw.write("#test=test\n");
        fw.close();

        Config config = new Config(file.getPath());
        config.load();
        assertNull(config.value("value"));

    }


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