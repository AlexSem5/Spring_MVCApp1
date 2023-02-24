package ru.alexsem.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alexsem.springcourse.dao.PersonDAO;
import ru.alexsem.springcourse.models.Person;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PersonDAO personDAO;
    
    @Autowired
    public AdminController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
    
    @GetMapping()
    public String adminPage(Model model, @ModelAttribute("person") Person person) {
        List<Person> people = personDAO.index();
        model.addAttribute("people", people);
        return "adminPage";
    }
    
    /**
     * Можем использовать и метод Patch.
     * Аннотация @ModelAttribute создаёт пустой объект класса Person,
     * видит, что с формы приходит значение id и назначает полю данное значение.
     * Из select будет приходить одно значение - значение id.
     * Это значение будет положено в value в форме: th:value="${person.getId()}.
     */
    @PostMapping("/add")
    public String makeAdmin(@ModelAttribute("person") Person person) {
        System.out.println(person.getId());
//        Значение других полей не будут инициализированы
        return "redirect:/people";
    }
}
