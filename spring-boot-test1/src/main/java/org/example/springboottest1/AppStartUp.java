package org.example.springboottest1;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStartUp implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("runnn");
    }
}
