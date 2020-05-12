package io.utils;

public class ArgsParser {

    static public SearchParams parseArgs(String[] args) {
        SearchParams result = new SearchParams();
        var index = 0;
        while (index < args.length) {
            switch (args[index]) {
                case "-d":
                    result.setPath(++index < args.length ? args[index++] : null);
                    break;
                case "-n":
                    result.setTargetName(++index < args.length ? args[index++] : "");
                    break;
                case "-m":
                    if (result.getSearchType() == null) {
                        result.setSearchType(new MaskSearchType());
                    } else {
                        result.incErrors();
                    }
                    index++;
                    break;
                case "-f":
                    if (result.getSearchType() == null) {
                        result.setSearchType(new NameSearchType());
                    } else {
                        result.incErrors();
                    }
                    index++;
                    break;
                case "-r":
                    if (result.getSearchType() == null) {
                        result.setSearchType(new RegexSearchType());
                    } else {
                        result.incErrors();
                    }
                    index++;
                    break;
                case "-o":
                    result.setLogFile(++index < args.length ? args[index++] : "");
                    break;
                default:
                    result.incErrors();
                    break;
            }
        }

        return result;
    }
}
