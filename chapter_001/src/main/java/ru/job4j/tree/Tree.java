package ru.job4j.tree;

import java.util.*;


public class Tree<E extends Comparable<E>> implements SimpleTree<E> {


    private final Node<E> root;

    public Tree(E value) {
        this.root = new Node<E>(value);
    }

    @Override
    public boolean add(E parent, E child) {
        var result = false;
        var parentNode = this.findBy(parent);
        if(!parentNode.isEmpty()) {
            parentNode.get().add(new Node<E>(child));
            result = true;
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            Queue<Node<E>> data = new LinkedList<>();

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public E next() {
                return null;
            }
        };
    }
}
