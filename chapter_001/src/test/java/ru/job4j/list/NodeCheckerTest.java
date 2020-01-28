package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import ru.job4j.list.utils.*;

public class NodeCheckerTest {

    NodeChecker<Node<Integer>> checker;

    @Before
    public void init() {
        checker = new NodeChecker<>();
    }

    @Test
    public void testCircle() {
        Node first = new Node(1);
        Node two = new Node(2);
        Node third = new Node(3);
        Node four = new Node(4);

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;

        assertThat(checker.hasCycle(first), is(true));
        assertThat(checker.hasCycle(two), is(true));
    }

    @Test
    public void testCircleInMiddle() {
        Node first = new Node(5);
        Node two = new Node(6);
        Node third = new Node(7);
        Node four = new Node(8);

        first.next = two;
        two.next = third;
        third.next = two;
        four.next = first;

        assertThat(checker.hasCycle(first), is(true));
        assertThat(checker.hasCycle(third), is(true));
    }

    @Test
    public void testCircleInMiddleLarger() {
        Node first = new Node(5);
        Node two = new Node(6);
        Node third = new Node(7);
        Node four = new Node(8);
        Node five = new Node(8);

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = two;
        five.next = first;

        assertThat(checker.hasCycle(first), is(true));
        assertThat(checker.hasCycle(third), is(true));
    }

}