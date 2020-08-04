package ru.job4j.tracker.singleton;

import org.junit.Test;
import ru.job4j.tracker.Item;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

public class SingletonClassesTest {
    @Test
    public void singletonEnumTest() {
        TrackerSingleEnum tSE1 = TrackerSingleEnum.INSTANCE;
        Item item = tSE1.add(new Item("item"));
        TrackerSingleEnum tSE2 = TrackerSingleEnum.INSTANCE;
        assertThat(tSE2.get().getId(), is(item.getId()));
        assertSame(tSE2, tSE1);
    }

    @Test
    public void singletonStaticFieldTest() {
        TrackerSingleStaticField tSSF1 = TrackerSingleStaticField.getInstance();
        Item item = tSSF1.add(new Item("Item"));
        TrackerSingleStaticField tSSF2 = TrackerSingleStaticField.getInstance();
        assertThat(tSSF2.get().getId(), is(item.getId()));
        assertSame(tSSF2, tSSF1);
    }

    @Test
    public void singletonStaticClassTest() {
        TrackerSingleStaticFinalClass tSSFC1 = TrackerSingleStaticFinalClass.getInstance();
        Item item = tSSFC1.add(new Item("item"));
        TrackerSingleStaticFinalClass tSSFC2 = TrackerSingleStaticFinalClass.getInstance();
        assertThat(tSSFC2.get().getId(), is(item.getId()));
        assertSame(tSSFC2, tSSFC1);
    }


    @Test
    public void singletonStaticFinalFieldTest() {
        TrackerSingleStaticFinalField tSSFFF1 = TrackerSingleStaticFinalField.getInstance();
        Item item = tSSFFF1.add(new Item("item"));
        TrackerSingleStaticFinalField tSSFFF2 = TrackerSingleStaticFinalField.getInstance();
        assertThat(tSSFFF2.get().getId(), is(item.getId()));
        assertSame(tSSFFF2, tSSFFF1);
    }

}
