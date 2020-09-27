package reference;

import gc.Demonstration;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ref.SoftReference;
import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FileCacheTest {

    @Rule
    public TemporaryFolder tmpFolder = new TemporaryFolder();

    @Test
    public void testCache() throws IOException {
        var fileCache = new FileCache();
        var adressFile = tmpFolder.newFile("Adress.txt");
        try (var adressPrinter = new PrintWriter(adressFile)) {
            adressPrinter.println("Moscow;");
            adressPrinter.println("Kiev;");
        }
        var namesFile = tmpFolder.newFile("Names.txt");
        try (var namesPrinter = new PrintWriter(namesFile)) {
            namesPrinter.println("Ivan;");
            namesPrinter.println("Nikolay;");
        }

        assertThat(fileCache.getFile(adressFile.getPath()), is("Moscow;Kiev;"));
        assertThat(fileCache.getFile(namesFile.getPath()), is("Ivan;Nikolay;"));
    }

}