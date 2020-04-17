package io.utils;

public class NameSearchType implements SearchType {
    @Override
    public boolean compare(String first, String second) {
        return first.equals(second);
    }
}
