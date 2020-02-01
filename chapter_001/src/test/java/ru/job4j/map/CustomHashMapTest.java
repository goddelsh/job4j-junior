package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

public class CustomHashMapTest {

    public CustomHashMap<? super SimpleUnit, Integer> customMap;

    @Before
    public void init() {
        customMap = new CustomHashMap();
    }


    @Test
    public void insertUniq() {
        SimpleUnit unit1 = new SimpleUnit("unit");
        SimpleUnit unit2 = new SimpleUnit("unit");
        customMap.insert(unit1, 1);
        customMap.insert(unit2, 2);
        System.out.print(customMap);
        assertThat(customMap.getElementCount(), is(2));
    }

    @Test
    public void insertSame() {
        SimpleUnit unit = new SimpleUnit("unit2");
        customMap.insert(unit, 1);
        customMap.insert(unit, 2);
        System.out.print(customMap);
        assertThat(customMap.getElementCount(), is(1));
    }

    @Test
    public void insertWithOveriddedHashcodeExpectedCollision() {
        SimpleUnitHashCode unit1 = new SimpleUnitHashCode("unit");
        SimpleUnitHashCode unit2 = new SimpleUnitHashCode("unit");
        customMap.insert(unit1, 1);
        assertThat(customMap.insert(unit2, 1), is(false));
        System.out.print(customMap);
    }

    @Test
    public void checkMapResize() {
        for (int i = 0; i < 1000; i++) {
            customMap.insert(new SimpleUnitHashCode(String.valueOf(i)), i);
        }
        assertThat(customMap.getElementCount(), greaterThan(16));
    }


    @Test
    public void delete() {
        customMap.insert(new SimpleUnitHashCode("unit1"), 1);
        customMap.insert(new SimpleUnitHashCode("unit2"), 2);
        customMap.insert(new SimpleUnitHashCode("unit3"), 3);
        assertThat(customMap.getElementCount(), is(3));
        assertThat(customMap.delete(new SimpleUnitHashCode("unit2")), is(true));
        assertThat(customMap.getElementCount(), is(2));
    }

    @Test
    public void iterator() {
        customMap.insert(new SimpleUnitHashCode("unit1"), 1);
        customMap.insert(new SimpleUnitHashCode("unit2"), 2);
        customMap.insert(new SimpleUnitHashCode("unit3"), 3);
        var it = customMap.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next().key.hashCode(), is(new SimpleUnitHashCode("unit1").hashCode()));
        assertThat(it.next().key.hashCode(), is(new SimpleUnitHashCode("unit2").hashCode()));
        assertThat(it.next().key.hashCode(), is(new SimpleUnitHashCode("unit3").hashCode()));
        assertThat(it.hasNext(), is(false));
    }

    public static class SimpleUnit {
        String title;

        public SimpleUnit(String title) {
            this.title = title;
        }
    }

    public static class SimpleUnitHashCode extends SimpleUnit {

        public SimpleUnitHashCode(String title) {
            super(title);
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        public int hashCode() {
            return title != null ? title.hashCode() : 0;
        }
    }


}