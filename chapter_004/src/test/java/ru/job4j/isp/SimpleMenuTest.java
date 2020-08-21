package ru.job4j.isp;

//import junit.framework.TestCase;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleMenuTest {


    // поле содержит дефолтный вывод в консоль.
    private final PrintStream stdout = System.out;
    // буфер для результата.
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }
    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void testPrintMenu() {
        Menu menu = new SimpleMenu();


        menu.addItem("Задача 1", null, null);
        menu.addItem("Задача 2", null, null);
        menu.addItem("Задача 1.1", "Задача 1", null);
        menu.addItem("Задача 2.1", "Задача 2", null);

        menu.printMenu();
        StringBuilder expected = new StringBuilder("Задача 1").append(System.lineSeparator())
                .append("Задача 1.1").append(System.lineSeparator())
                .append("Задача 2").append(System.lineSeparator())
                .append("Задача 2.1").append(System.lineSeparator());

        assertThat(new String(out.toByteArray()), is(expected.toString()));
    }

    @Test
    public void testFindByName() {
        Menu menu = new SimpleMenu();


        menu.addItem("Задача 1", null, null);
        menu.addItem("Задача 2", null, null);
        menu.addItem("Задача 3", null, null);
        menu.addItem("Задача 1.1", "Задача 1", null);
        menu.addItem("Задача 1.2", "Задача 1", null);
        menu.addItem("Задача 1.3", "Задача 1", null);
        menu.addItem("Задача 2.1", "Задача 2", null);

        assertThat(menu.findByName("Задача 3").getName(), is("Задача 3"));
        assertThat(menu.findByName("Задача 3").getName(), is("Задача 3"));
        assertThat(menu.findByName("Задача 2.1").getName(), is("Задача 2.1"));

    }

    @Test
    public void testActivateItems() {
        Menu menu = new SimpleMenu();

        var action = new ItemLogic() {
            @Override
            public void activate(Item item) {
                System.out.println(item.getName() + " activated!");
            }
        };

        menu.addItem("Задача 1", null, action);
        menu.addItem("Задача 2", null, action);
        menu.addItem("Задача 1.1", "Задача 1", action);
        menu.addItem("Задача 2.1", "Задача 2", action);


        menu.findByName("Задача 1.1").doAction();
        assertThat(new String(out.toByteArray()),
                is(new StringBuilder("Задача 1.1 activated!").append(System.lineSeparator()).toString()));
    }


}