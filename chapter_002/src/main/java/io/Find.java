package io;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class Find {

    private int count;


    private Find(String path, String targetName, int searchType, String logFile) {
        if (writeToLog(getFileList(path, targetName, searchType), logFile)) {
            System.out.printf("%d file founded. Logged to %s", count, logFile);
        }
    }

    private List<File> getFileList(String path, String targetName, int searchType) {
        return new Search().files(path, List.of(targetName), (name, list) -> {
            var res = false;
            for (String el : list) {
                if (((searchType == 0)  &&  name.matches(el.replace(".", "\\.").replace("*", ".*")))
                        || ((searchType == 1) && name.equals(el))
                        || ((searchType == 2) && name.contains(el))) {
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
        int searchType = -1;
        String logFile = null;
        int errors = 0;


        while (index < args.length) {
            switch (args[index]) {
                case "-d":
                    path = ++index < args.length ? args[index++] : null;
                    break;
                case "-n":
                    targetName = ++index < args.length ? args[index++] : "";
                    break;
                case "-m":
                    searchType = searchType < 0 ? 2 : errors++;
                    index++;
                    break;
                case "-f":
                    searchType = searchType < 0 ? 1 : errors++;
                    index++;
                    break;
                case "-r":
                    searchType = searchType < 0 ? 0 : errors++;
                    index++;
                    break;
                case "-o":
                    logFile = ++index < args.length ? args[index++] : "";
                    break;
                default:
                    System.out.println("Unknown args");
                    errors++;
                    break;
            }
        }

        if ((errors == 0) && (path != null) && (logFile != null)) {
            new Find(path, targetName, searchType < 0 ? 2 : searchType, logFile);
            System.out.println("Completed.");
        } else {
            System.out.printf("Problems with arguments. Errors %d \n Target path %s \n Log file %s \n",
                    errors, path, logFile);
            Find.printHelp();
        }
    }
}
