package org.example.springboottest1;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Employee {


    @GetMapping("/re")
    public String getName(){
        return "Remotlly";
    }
}
