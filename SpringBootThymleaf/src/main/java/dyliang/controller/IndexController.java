package dyliang.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import dyliang.domain.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class IndexController {

    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("count", 10);
        return "index";
    }

    @GetMapping("/person")
    public String person(Model model){
        model.addAttribute("person", Person.builder().id(10).name("Forlogen").build());
        model.addAttribute("person1", Person.builder().id(2).name("Forlogen").build());

        List<Person> persons = new ArrayList<>();
        Collections.addAll(persons,
                Person.builder().id(1).name("Forlgoen").build(),
                Person.builder().id(2).name("kobe").build());

        model.addAttribute("persons", persons);

        return "index";
    }

    @GetMapping("/person/list")
    public String list(Model model){
        List<Person> persons = new ArrayList<>();
        Collections.addAll(persons,
                Person.builder().id(1).name("Forlgoen").build(),
                Person.builder().id(2).name("kobe").build());

        model.addAttribute("persons", persons);

        return "index :: personList";
    }
}