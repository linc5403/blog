package club.banyuan;

import club.banyuan.bean.Person;
import lombok.var;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class TestController {

    @GetMapping("/blog")
    @ResponseBody
    String getBlogById(@RequestParam(name="number", required=false, defaultValue="10")
                               Integer id) {
        return id.toString();
    }

    @GetMapping("/person")
    @ResponseBody
    Person getPerson(@RequestParam String name,
                     @RequestParam Integer age) {
        var newPerson = new Person(name, age);
        return newPerson;
    }

    @GetMapping("/personObj")
    @ResponseBody
    Person getPersonObj(@Valid Person newPerson) {
        return newPerson;
    }
}
