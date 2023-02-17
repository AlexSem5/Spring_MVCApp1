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
        people.add((new Person(++PEOPLE_COUNT, "Tom", 25, "tom@gmail.com")));
        people.add((new Person(++PEOPLE_COUNT, "Bob", 35, "bob@gmail.com")));
        people.add((new Person(++PEOPLE_COUNT, "Mike", 45, "mike@gmail.com")));
        people.add((new Person(++PEOPLE_COUNT, "Kate", 55,"kate@gmail.com")));
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
    
    public void update(int id, Person updatedPerson) {
        Person personToUpdate = show(id);
        personToUpdate.setName(updatedPerson.getName());
        personToUpdate.setAge(updatedPerson.getAge());
        personToUpdate.setEmail(updatedPerson.getEmail());
    }
    
    public void delete(int id) {
        people.removeIf(person -> person.getId() == id);
    }
}
