package dyliang.controller;

import dyliang.domain.Person;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DataController {
    /**
     * http://localhost:8080/user/1?name=Forlgoen
     *
     * 控制台输出：1 Forlgoen
     *
     * @param id
     * @param name
     * @return
     */
    @ResponseBody
    @GetMapping("user/{id}")
    public String test(@PathVariable(name = "id")Integer id, @RequestParam(name = "name") String name){
        System.out.println(id + " " + name);
        return "success";
    }

    @ResponseBody
    @GetMapping("/person")
    public String test2(@RequestBody Person person){
        System.out.println(person);

        return "success";
    }
}
