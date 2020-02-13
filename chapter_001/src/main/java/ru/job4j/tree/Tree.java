package ru.job4j.tree;

import javax.swing.text.html.HTMLDocument;
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
        if (!parentNode.isEmpty() && checkUniq(child)) {
            parentNode.get().add(new Node<E>(child));
            result = true;
        }
        return result;
    }

    private boolean checkUniq(E child) {
        var result = true;
        var it = this.iterator();
        while (it.hasNext()) {
            if (it.next().equals(child)) {
                result = false;
                break;
            }
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


    public boolean isBinary() {
        boolean result = true;
        Queue<Node<E>> data = new LinkedList<Node<E>>(List.of(root));
        while (data.size() > 0) {
            Node<E> node = data.poll();
            if (node.leaves().size() > 2) {
                result = false;
                break;
            }
            for (Node<E> child : node.leaves()) {
                data.offer(child);
            }
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            Queue<Node<E>> data = new LinkedList<Node<E>>(List.of(root));


            @Override
            public boolean hasNext() {
                return data.size() > 0 ? true : false;
            }

            @Override
            public E next() {
                E result = null;
                if (hasNext()) {
                    Node<E> node = data.poll();
                    result = node.getValue();
                    for (Node<E> child : node.leaves()) {
                        data.offer(child);
                    }
                }
                return result;
            }
        };
    }
}
