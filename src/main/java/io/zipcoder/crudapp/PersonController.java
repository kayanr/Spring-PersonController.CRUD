package io.zipcoder.crudapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/people")
public class PersonController {

    /*Create a PersonController class with Person createPerson(Person p), Person getPerson(int id),
    *List<Person> getPersonList(), Person updatePerson(Person p),and void DeletePerson(int id) methods,
    * and let it track a list of Person objects.*/

    @Autowired
    private PersonRepository personRepository;

    private List<Person> personList = new ArrayList<>();
    private int idCounter = 1;

    @PostMapping
    public Person createPerson(@RequestBody Person p){
        return personRepository.save(p);
    }

    @GetMapping("/{id}")
    public Optional<Person> getPerson(@PathVariable Integer id){
       return personRepository.findById(id);
    }

    @GetMapping
    public Iterable<Person> getPersonList(){
        return personRepository.findAll();
    }

    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable Integer id, @RequestBody Person p){
        p.setId(id);
        return personRepository.save(p);
    }

    @DeleteMapping("/{id}")
    public void DeletePerson(@PathVariable Integer id){
        personRepository.deleteById(id);

    }
}
