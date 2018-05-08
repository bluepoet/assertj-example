package net.bluepoet.assertjex;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class CoreFeatureEx {
    @Test
    public void describeAssertion() throws Exception {
        // Given
        // When
        Person person = new Person("bluepoet", 20);

        // Then
        assertThat(person.getAge()).as("check %s's age", person.getName()).isEqualTo(20);
        assertThat(person.getName()).as("check test result person name").isEqualTo("bluepoet");
    }

    private class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
