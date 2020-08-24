package ru.job4j.template;

import org.junit.Test;
import org.junit.Ignore;
import java.util.Map;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class GeneratorTest {

    @Ignore
    @Test
    public void checkWithTwoArguments() {
        assertThat(new Generator() {
                    @Override
                    public String produce(String template, Map<String, String> args) {
                        return null;
                    }
                }.produce("My name is ${name}. I am from ${country}",
                Map.of("name", "Ruslan", "country",
                        "Afghanistan")),
                is("My name is Ruslan. I am from Afghanistan"));
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void checkWithIllegallArguments() {
        var result = new Generator() {
                    @Override
                    public String produce(String template, Map<String, String> args) {
                        return null;
                    }
                }.produce("My name is ${name}. I am from ${country}.",
                Map.of("name", "Ruslan", "country", "${country}"));
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void checkWithArgumentsMoreThanInTemplate() {
        var result = new Generator() {
            @Override
            public String produce(String template, Map<String, String> args) {
                return null;
            }
        }.produce("My name is ${name}. I am from ${country}",
                Map.of("name", "Ruslan", "country", "Afghanistan",  "Sex", "Male"));
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void checkWithArgumentsLessThanInTemplate() {
        var result = new Generator() {
            @Override
            public String produce(String template, Map<String, String> args) {
                return null;
            }
        }.produce("My name is ${name}. I am from ${country}",
                Map.of("name", "Ruslan"));
    }

    @Ignore
    @Test
    public void checkWithRepeatsInTemplate() {
        assertThat(new Generator() {
                    @Override
                    public String produce(String template, Map<String, String> args) {
                        return null;
                    }
                }.produce("My name is ${name}. I am from ${country}. I left ${country}...${name}",
                Map.of("name", "Ruslan", "country", "Afghanistan")),
                is("My name is Ruslan. I am from Afghanistan. I left Afghanistan...Ruslan"));
    }
}