package ru.alexsem.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.alexsem.springcourse.models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;
    
    {
        people = new ArrayList<>();
        people.add((new Person(++PEOPLE_COUNT, "Tom")));
        people.add((new Person(++PEOPLE_COUNT, "Bob")));
        people.add((new Person(++PEOPLE_COUNT, "Mike")));
        people.add((new Person(++PEOPLE_COUNT, "Kate")));
    }
    
    public List<Person> index() {
        return people;
    }
    
    public Person show(int id) {
        return people.stream()
                     .filter(person -> person.getId() == id)
                     .findAny()
                     .orElse(null);
    }
    
    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }
}
