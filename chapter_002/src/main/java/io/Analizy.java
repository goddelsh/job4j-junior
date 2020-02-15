package io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.regex.Pattern;

public class Analizy {
    private String periodLine;
    private final Pattern logPattern = Pattern.compile("([0-9]{3}) ([0-9]{2}:[0-9]{2}:[0-9]{2})");

    private void periodsCount(String line, PrintWriter out) {
        if (logPattern.matcher(line).matches()) {
            var data = line.split(" ");
            if ((data[0].equals("500") || data[0].equals("400")) && (this.periodLine == null)) {
                this.periodLine = data[1] + ";";
            } else if ((data[0].equals("200") || data[0].equals("300")) && (this.periodLine != null)) {
                this.periodLine += data[1] + ";";
                writePeriod(out);
            }
        } else if (line.length() > 0) {
            throw new IllegalStateException("Format exception");
        }
    }

    private void writePeriod(PrintWriter out) {
        out.println(this.periodLine);
        this.periodLine = null;
    }

    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))
        ) {
            read.lines().forEach(line -> periodsCount(line, out));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
