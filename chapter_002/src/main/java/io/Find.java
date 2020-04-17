package io;

import io.utils.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class Find {

    private int count;


    private Find(Args args) {
        if (writeToLog(getFileList(args), args.getLog())) {
            System.out.printf("%d file founded. Logged to %s", count, args.getLog());
        }
    }

    private List<File> getFileList(Args args) {
        return new Search().files(args.getPath(), List.of(args.getTarget()), (name, list) -> {
            var res = false;
            for (String el : list) {
                if (args.comparing(name, el)) {
                    res = true;
                }
            }
            return res;
        });
    }


    private boolean writeToLog(List<File> list, String log) {
        var result = false;
        try (PrintStream printStream = new PrintStream(new File(log))) {
            list.forEach(printStream::println);
            result = true;
            this.count = list.size();
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return result;
    }

    private static void printHelp() {
        System.out.println("Searching programm");
        System.out.println("find.jar -d [searchable path] -n [name, mask or regex] -m|-f|-r -o [log file]");
        System.out.println("-m search by mask ");
        System.out.println("-f search by name ");
        System.out.println("-r search by regex ");
        System.out.println();
    }

    public static void main(String[] args) {
        var index = 0;

        String path = null;
        String targetName = "";
        String logFile = null;
        int errors = 0;

        SearchType searchType = null;

        while (index < args.length) {
            switch (args[index]) {
                case "-d":
                    path = ++index < args.length ? args[index++] : null;
                    break;
                case "-n":
                    targetName = ++index < args.length ? args[index++] : "";
                    break;
                case "-m":
                    if (searchType == null) {
                        searchType = new MaskSearchType();
                    } else {
                        errors++;
                    }
                    index++;
                    break;
                case "-f":
                    if (searchType == null) {
                        searchType = new NameSearchType();
                    } else {
                        errors++;
                    }
                    index++;
                    break;
                case "-r":
                    if (searchType == null) {
                        searchType = new RegexSearchType();
                    } else {
                        errors++;
                    }
                    index++;
                    break;
                case "-o":
                    logFile = ++index < args.length ? args[index++] : "";
                    break;
                default:
                    System.out.println("Unknown params");
                    errors++;
                    break;
            }
        }

        if ((errors == 0) && (path != null) && (logFile != null)) {
            new Find(new Args(searchType, path, targetName, logFile));
            System.out.println("Complete.");
        } else {
            System.out.printf("Problems with argements. Errors %d \n Path for search %s \n Lof file %s \n",
                    errors, path, logFile);
            Find.printHelp();
        }
    }
}
