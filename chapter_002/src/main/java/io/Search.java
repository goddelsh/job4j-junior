package io;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Search {
    List<File> files(String parent, List<String> exts) {
        List<File> result = new ArrayList<File>();
        File file = new File(parent);
        Queue<File> queue = new LinkedList<>();

        if (file.exists()) {
            queue.add(file);
            while (queue.size() > 0) {
                File f = queue.poll();
                if (f.isDirectory()) {
                    queue.addAll(List.of(f.listFiles()));
                } else {
                    if (checkCoincidence(f.getName(), exts)) {
                        result.add(f);
                    }
                }
            }

        }
        return result;
    }

    private boolean checkCoincidence(String target, List<String> comparingList) {
        var result = false;
        for (String el : comparingList) {
            if (target.endsWith(el)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
