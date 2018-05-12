package net.bluepoet.assertjex;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class CoreFeatureEx {
    List<Person> persons = new ArrayList<>();
    Person tester =  new Person("teser", 10);
    Person bluepoet =  new Person("bluepoet", 30);
    Person lover =  new Person("lover", 50);

    @Before
    public void setUp() throws Exception {
        persons.add(tester);
        persons.add(bluepoet);
        persons.add(lover);

        persons.get(0).addHobby(Hobby.create("football"));
        persons.get(2).addHobby(Hobby.create("baseball"));
    }

    @Test
    public void describeAssertion() throws Exception {
        // Given
        // When
        Person bluepoet = persons.get(1);

        // Then
        assertThat(bluepoet.getAge()).as("check %s's age", bluepoet.getName()).isEqualTo(30);
        assertThat(bluepoet.getName()).as("check test result person name").isEqualTo("bluepoet");
    }

    @Test
    public void combileFilterAndAssertions() {
        // given
        // when
        // then
        assertThat(persons).filteredOn(p -> p.getAge() >= 30).as("나이가 30살 이상인 사람은 몇명일까?").hasSize(2);
    }

    @Test
    public void filterPropertyOrField() {
        // given
        // when
        // then
        assertThat(persons).filteredOn("hobby", Hobby.create("football")).containsOnly(tester);
    }

    private class Person {
        private String name;
        private int age;
        private Hobby hobby;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Person(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public void addHobby(Hobby hobby) {
            this.hobby = hobby;
        }
    }

    private static class Hobby {
        private String name;

        private Hobby(String name) {
            this.name = name;
        }

        private static Hobby create(String name) {
            return new Hobby(name);
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Hobby hobby = (Hobby) o;
            return Objects.equals(name, hobby.name);
        }

        @Override
        public int hashCode() {

            return Objects.hash(name);
        }
    }
}
