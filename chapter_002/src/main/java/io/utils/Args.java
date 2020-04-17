package io.utils;

public class Args {
    private SearchType searchType;

    private String path;
    private String target;
    private String log;

    public Args(SearchType searchType, String path, String target, String log) {
        this.searchType = searchType;
        this.path = path;
        this.target = target;
        this.log = log;
    }

    public String getPath() {
        return path;
    }

    public String getTarget() {
        return target;
    }

    public String getLog() {
        return log;
    }

    public boolean comparing(String first, String second) {
        return searchType.compare(first, second);
    }
}
