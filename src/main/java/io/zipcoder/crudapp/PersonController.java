package io.zipcoder.crudapp;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {

    /*Create a PersonController class with Person createPerson(Person p), Person getPerson(int id),
    *List<Person> getPersonList(), Person updatePerson(Person p),and void DeletePerson(int id) methods,
    * and let it track a list of Person objects.*/

    private List<Person> personList = new ArrayList<>();
    private int idCounter = 1;

    @PostMapping
    public Person createPerson(@RequestBody Person p){
        p.setId(idCounter++);
        personList.add(p);
        return p;
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable int id){
       return personList.stream().filter(person -> person.getId() == id).findFirst().orElse(null);
    }

    @GetMapping
    public List<Person> getPersonList(){
        return personList;
    }

    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable int id, @RequestBody Person p){
        Person person = getPerson(id);
        if(person != null){
            person.setFirstName((p.getFirstName()));
            person.setLastName(p.getLastName());
        }else{
            p.setId(id);
            personList.add(p);
        }
        return p;
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable int id){
        personList.removeIf(person -> person.getId() == id);

    }
}
