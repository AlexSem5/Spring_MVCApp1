package ru.alexsem.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alexsem.springcourse.dao.PersonDAO;
import ru.alexsem.springcourse.models.Person;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;
    
    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
    
    //    В методе index в @GetMapping не передаём что-либо, так
//    как в URL будет только /people
    @GetMapping()
    public String index(Model model) {
//        Получим всех людей из DAO и передадим на отображение в представление
        List<Person> people = personDAO.index();
        model.addAttribute("people",people);
        return "people/index";
    }
    
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
//        Получим одного человека из DAO и передадим на отображение в представление
        Person person = personDAO.show(id);
        model.addAttribute("person",person);
        return "people/show";
    }
}
