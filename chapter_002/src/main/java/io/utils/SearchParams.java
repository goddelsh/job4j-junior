package io.utils;

public class SearchParams {
    private String path = null;
    private String targetName = "";
    private String logFile = null;
    private int errors = 0;
private SearchType searchType;

    public boolean comparing(String first, String second) {
        return searchType.compare(first, second);
    }

    public boolean selfCheck() {
        var result = false;
        if ((errors == 0) && (path != null) && (logFile != null)) {
            result = true;
        }
        return result;
    }

    SearchType getSearchType() {
        return searchType;
    }

    void setSearchType(SearchType searchType) {
        this.searchType = searchType;
    }

    public String getPath() {
        return path;
    }

    void setPath(String path) {
        this.path = path;
    }

    public String getTargetName() {
        return targetName;
    }

    void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getLogFile() {
        return logFile;
    }

    void setLogFile(String logFile) {
        this.logFile = logFile;
    }

    void incErrors() {
        errors++;
    }

    public int getErrors() {
        return errors;
    }
}
