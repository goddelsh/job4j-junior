package ru.job4j.list;

import ru.job4j.list.utils.*;

public class NodeChecker<E extends Node> {

    boolean hasCycle(E first) {
        boolean result = false;
        E  compared = first;
        E next = first;
        while (next != null) {
            next = (E) next.getNext();
            if (next == null) {
                break;
            } else if (next == compared || next == first) {
                result = true;
                break;
            }
            next = (E) next.getNext();
            if (compared.equals(next)) {
                result = true;
                break;
            }
            compared = (E) compared.getNext();
        }
        return result;
    }



}
