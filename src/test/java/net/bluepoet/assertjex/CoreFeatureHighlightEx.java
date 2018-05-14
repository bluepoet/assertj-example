package net.bluepoet.assertjex;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class CoreFeatureHighlightEx {
    List<Person> persons = new ArrayList<>();
    Person tester = new Person("tester", 10);
    Person bluepoet = new Person("bluepoet", 30);
    Person lover = new Person("lover", 50);
    Person poet = new Person("poet", 60);

    @Before
    public void setUp() throws Exception {
        tester.addHobby(Hobby.PIANO);
        bluepoet.addHobby(Hobby.READING);
        lover.addHobby(Hobby.MOVIE);
        poet.addHobby(Hobby.PIANO);

        persons.add(tester);
        persons.add(bluepoet);
        persons.add(lover);
        persons.add(poet);
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
        assertThat(persons).filteredOn(p -> p.getAge() >= 30).as("나이가 30살 이상인 사람은 몇명일까?").hasSize(3);
    }

    @Test
    public void filterPropertyOrField() {
        // given
        // when
        // then
        assertThat(persons).filteredOn("hobby", Hobby.PIANO).containsOnly(tester, poet);
        assertThat(persons).filteredOn("hobby.name", "reading").containsOnly(bluepoet);
        assertThat(persons).filteredOn("hobby", notIn(Hobby.PIANO, Hobby.READING)).containsOnly(lover);
        assertThat(persons).filteredOn("hobby", in(Hobby.MOVIE)).containsOnly(lover);
        assertThat(persons).filteredOn("hobby", in(Hobby.WALKING)).isEmpty();
        assertThat(persons).filteredOn("hobby", not(Hobby.PIANO)).containsOnly(bluepoet, lover);

        assertThat(persons).filteredOn("hobby", Hobby.PIANO)
                .filteredOn("name", not("tester"))
                .containsOnly(poet);
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

    private enum Hobby {
        PIANO("piano"),
        WALKING("walking"),
        READING("reading"),
        MOVIE("movie");

        private String name;

        Hobby(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
