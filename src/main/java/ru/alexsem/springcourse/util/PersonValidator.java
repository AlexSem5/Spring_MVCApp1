package ru.alexsem.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alexsem.springcourse.dao.PersonDAO;
import ru.alexsem.springcourse.models.Person;

@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;
    
    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
    
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }
    
    @Override
    public void validate(Object target, Errors errors) {
//        Первая проверка: Посмотреть, есть ли человек с таким же email в БД
        Person person = (Person) target;
        if (personDAO.show(person.getEmail())
                     .isPresent()) {
            errors.rejectValue("email", "", "This email is already taken");
        }
//        Вторая проверка: Проверяем, что у человека имя начинается с заглавной буквы
//        Если не с заглавной, то добвляем ошибку
        if (!Character.isUpperCase(person.getName()
                                        .codePointAt(0))) {
            errors.rejectValue(
                    "name", "", "Name should start with a capital letter");
        }
    }
}
