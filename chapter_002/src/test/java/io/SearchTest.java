package io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SearchTest {


    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();


    @Test
    public void files() throws  Exception {
        var file = temporaryFolder.newFolder("grandparent", "parent", "child");
        var firstFile = File.createTempFile("temp",".txt",file);
        var secondFile = File.createTempFile("server",".log",file.getParentFile());
        var thirdFile = File.createTempFile("server",".txt",file.getParentFile());
        var fourthFile = File.createTempFile("ssss",".rar",file.getParentFile().getParentFile());

        Search search = new Search();
        var result = search.files(file.getParentFile().getParentFile().getPath(), List.of(".txt",".rar"));
        assertThat(result.size(), is(3));
        assertThat(result.get(0), is(fourthFile));
    }
}