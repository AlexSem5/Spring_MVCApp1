package ru.alexsem.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        model.addAttribute("people", people);
        return "people/index";
    }
    
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
//        Получим одного человека из DAO и передадим на отображение в представление
        Person person = personDAO.show(id);
        model.addAttribute("person", person);
        return "people/show";
    }
    
    //    Метод создаёт Person, добавляет объект в модель,
    //    возвращает html-форму. Далее поля инициализируются данными из формы
//    и передаются в метод create() по адресу POST /people
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
//        альтернативный вариант создания объекта Person:
//         model.addAttribute("person", new Person);
        return "people/new";
    }
    
    //   Метод принимает на вход post-запрос,
//   берёт данные из этого post-запроса
// и добавляет нового человека в базу данных с помощью DAO
    @PostMapping()
//  В модель будет положен новый объект Person со значениями полей из формы.
//  Аннотация @ModelAttribute ДЛЯ POST-ЗАПРОСА сама создаёт объект с вставленными в
//  форму полями и кладёт его в модель.
//  Альтернативный вариант:
//  Данные полей передаются в теле Post-request в формате name=Имя по адресу action="/people"
//  и далее извлекаются через @RequestParam и создаётся Person. См на салйде
//  CRUD_App2.
    public String create(@ModelAttribute("person") Person person) {
        personDAO.save(person);
        return "redirect:/people";
    }
}
