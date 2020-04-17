package io.utils;

public class MaskSearchType implements SearchType {
    @Override
    public boolean compare(String first, String second) {
        return first.contains(second);
    }
}
