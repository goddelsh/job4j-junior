package ru.job4j.iterator;

import java.util.Iterator;

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {

        return new Iterator<Integer>() {

            private Iterator<Integer> currerntIterator;

            private void checkCurrentIterator() {
                while (it.hasNext()) {
                    if (this.currerntIterator == null || !this.currerntIterator.hasNext()) {
                        this.currerntIterator = it.next();
                    } else {
                        break;
                    }
                }
            }

            @Override
            public boolean hasNext() {
                checkCurrentIterator();
                return this.currerntIterator.hasNext();
            }

            @Override
            public Integer next() {
                checkCurrentIterator();
                return this.currerntIterator.next();
            }
        };
    }
}
