package io;

import io.utils.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class Find {

    private int count;


    private Find(SearchParams args) {
        if (writeToLog(getFileList(args), args.getLogFile())) {
            System.out.printf("%d file founded. Logged to %s", count, args.getLogFile());
        }
    }

    private List<File> getFileList(SearchParams args) {
        return new Search().files(args.getPath(), List.of(args.getTargetName()), (name, list) -> {
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
        var searchParams = ArgsParser.parseArgs(args);
        if (searchParams.selfCheck()) {
            new Find(searchParams);
            System.out.println("Complete.");
        } else {
            System.out.printf("Problems with argements. Errors %d \n Path for search %s \n Lof file %s \n",
                    searchParams.getErrors(), searchParams.getPath(), searchParams.getLogFile());
            Find.printHelp();
        }
    }
}
