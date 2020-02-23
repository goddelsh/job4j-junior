package io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private List<String> excluded;
    private String path;
    private String target;

    public Zip(List<String> excluded, String path, String target) {
        this.excluded = excluded == null ? new ArrayList<>() : excluded;
        this.path = path;
        this.target = target;
    }

    public void pack() {
        List<File> files = new Search().files(this.path, null, (name, list) -> true);

        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : files) {
                if (checkExcludeList(file)) {
                    zip.putNextEntry(new ZipEntry(file.getPath()));
                    try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                        zip.write(out.readAllBytes());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean checkExcludeList(File file) {
        var result = true;
        for (String mask : this.excluded) {
            if (file.getName().matches(mask.replace(".", "\\.").replace("*", ".*"))) {
                result = false;
                break;
            }
        }
        return result;
    }


    public static void main(String[] args) {

        var index = 0;
        String path = null, target = null;
        List<String> excluded = null;
        while (index < args.length) {
            if (args[index].equals("-d")) {
                path = ++index < args.length ? args[index++] : "";
            } else if (args[index].equals("-e")) {
                excluded = ++index < args.length ? List.of(args[index++].split(",")) : new ArrayList<>();
            } else if (args[index].equals("-o")) {
                target = ++index < args.length ? args[index++] : "";
            } else {
                index++;
            }
        }

        new Zip(excluded, path, target).pack();

    }

}
