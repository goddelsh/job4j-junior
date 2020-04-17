package io.utils;

public class RegexSearchType implements SearchType {
    @Override
    public boolean compare(String first, String second) {
        return first.matches(second.replace(".", "\\.").replace("*", ".*"));
    }
}
