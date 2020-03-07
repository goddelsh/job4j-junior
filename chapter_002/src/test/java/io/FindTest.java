package io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FindTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();


    @Test
    public void findPropertiesFileByRegex() throws Exception {
        var log = temporaryFolder.newFile("log.txt");

        var file = temporaryFolder.newFolder("grandparent", "parent", "child");
        var firstFile = File.createTempFile("temp", ".txt", file);
        var secondFile = File.createTempFile("server", ".properties", file.getParentFile());
        var thirdFile = File.createTempFile("server", ".properties", file.getParentFile());
        var fourthFile = File.createTempFile("ssss", ".rar", file.getParentFile().getParentFile());

        Find.main(new String[]{"-d", file.getParentFile().getParentFile().getPath(), "-n", "*.properties", "-r", "-o", log.getPath()});

        try (BufferedReader logResult = new BufferedReader(new FileReader(log))) {
            assertThat(logResult.lines().count(), is(2L));
        }
    }

    @Test
    public void findPropertiesFileByMask() throws Exception {
        var log = temporaryFolder.newFile("log.txt");

        var file = temporaryFolder.newFolder("grandparent", "parent", "child");
        var firstFile = File.createTempFile("temp", ".txt", file);
        var secondFile = File.createTempFile("qwe", ".rar", file.getParentFile());
        var thirdFile = File.createTempFile("wwweee", ".rar", file.getParentFile());
        var fourthFile = File.createTempFile("ssss", ".rar", file.getParentFile().getParentFile());

        Find.main(new String[]{"-d", file.getParentFile().getParentFile().getPath(), "-n", ".rar", "-m", "-o", log.getPath()});

        try (BufferedReader logResult = new BufferedReader(new FileReader(log))) {
            assertThat(logResult.lines().count(), is(3L));
        }
    }

    @Test
    public void findPropertiesFileByName() throws Exception {
        var log = temporaryFolder.newFile("log.txt");

        var file = temporaryFolder.newFolder("grandparent", "parent", "child");
        var firstFile = File.createTempFile("temp", ".txt", file);
        var secondFile = File.createTempFile("server", ".properties", file.getParentFile());
        var thirdFile = File.createTempFile("server", ".properties", file.getParentFile());
        var fourthFile = File.createTempFile("ssss", ".rar", file.getParentFile().getParentFile());

        Find.main(new String[]{"-d", file.getParentFile().getParentFile().getPath(), "-n", firstFile.getName(), "-f", "-o", log.getPath()});

        try (BufferedReader logResult = new BufferedReader(new FileReader(log))) {
            assertThat(logResult.lines().count(), is(1L));
        }
    }

}