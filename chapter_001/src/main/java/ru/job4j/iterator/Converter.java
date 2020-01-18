package ru.job4j.iterator;

import java.util.Iterator;

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {

        return new Iterator<Integer>() {

            private Iterator<Integer> currerntIterator;

            @Override
            public boolean hasNext() {
                while (it.hasNext()) {
                    if (this.currerntIterator == null || !this.currerntIterator.hasNext()) {
                        this.currerntIterator = it.next();
                    } else {
                        break;
                    }
                }
                return this.currerntIterator.hasNext();
            }
            @Override
            public Integer next() {
                while (it.hasNext()) {
                    if (this.currerntIterator == null || !this.currerntIterator.hasNext()) {
                        this.currerntIterator = it.next();
                    } else {
                        break;
                    }
                }

                return this.currerntIterator.next();
            }
        };
    }
}
