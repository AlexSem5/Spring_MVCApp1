package ru.alexsem.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alexsem.springcourse.dao.PersonDAO;
import ru.alexsem.springcourse.models.Person;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

/**
 * HTTP методы и URL для паттерна REST указаны в CRUD_App1
 */
@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;
    
    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
    
    /**
     * Получаем все записи с сервера(DB->DAO->Controller->View)
     *
     * @param model
     * @return
     */
    @GetMapping()
    public String index(Model model) throws SQLException {
//  Получим всех людей из DAO и передадим на отображение в представление
        List<Person> people = personDAO.index();
        model.addAttribute("people", people);
        return "people/index";
    }
    
    /**
     * Получаем одну запись с сервера (DB->DAO->Controller->View)
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) throws SQLException {
//        Получим одного человека из DAO и передадим на отображение в представление
        Person person = personDAO.show(id);
        model.addAttribute("person", person);
        return "people/show";
    }
    
    /**
     * Метод возвращает с сервера html-форму (Thymeleaf) для создания человека.
     * Метод создаёт Person, добавляет объект в модель и отправляет в Thymeleaf.
     * Далее поля инициализируются данными из формы
     * и передаются в метод create() в POST запрос /people
     *
     * @param person
     * @return
     */
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
//        альтернативный вариант создания объекта Person:
//         model.addAttribute("person", new Person);
        return "people/new";
    }
    
    /**
     * Метод принимает на вход post-запрос по адресу /people,
     * берёт данные из этого post-запроса
     * и добавляет нового человека в базу данных с помощью DAO
     * View->Controller->DAO->DB
     * <p>
     * В модель будет положен новый объект Person со значениями полей из формы.
     * Аннотация @ModelAttribute ДЛЯ POST-ЗАПРОСА сама создаёт объект с вставленными в
     * форму полями и кладёт его в модель.
     * Альтернативный вариант:
     * Данные полей передаются ИЗ HTML-ФОРМЫ в теле Post-request в формате name=Имя
     * по адресу action="/people" и далее извлекаются через @RequestParam и
     * создаётся Person. См на салйдеCRUD_App2.
     *
     * @param person
     * @param bindingResult
     * @return
     */
    
    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        personDAO.save(person);
        return "redirect:/people";
    }
    
    /**
     * Метод возвращает с сервера html-страницу для редактирования человека.
     * Аннотация @PathVariable("id") позволяет извлечь id из адреса запроса
     * и кладёт в переменную int id. Далее мы внедряем в модель объект класса
     * Person  с уже имеющимися полями.
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) throws SQLException {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }
    
    /**
     * View->Controller->DAO->DB
     * Метод принимает Patch-запрос
     * Аннотация @ModelAttribute создаёт объект Person
     * с вставленными в форму полями и кладёт в переменную person.
     * Это альтернатива @RequestParam (см на салйдеCRUD_App2).
     *
     * @param person
     * @param bindingResult
     * @param id
     * @return
     */
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult, @PathVariable("id") int id) throws SQLException {
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        personDAO.update(id, person);
        return "redirect:/people";
    }
    
    /**
     * View->Controller->DAO->DB
     * Метод принимает Delete-запрос
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) throws SQLException {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
