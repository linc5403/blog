package club.banyuan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Calc {
    @GetMapping("/calc")
    String showCalcPage() {
        return "calc";
    }

    @PostMapping("/calc/add")
    @ResponseBody
    String add(int num1, int num2){
        return "" + num1 + " + " + num2 + " = " + (num1+num2);
    }
}
