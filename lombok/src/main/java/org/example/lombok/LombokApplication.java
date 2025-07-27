package org.example.lombok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LombokApplication {

    public static void main(String[] args) {

        SpringApplication.run(LombokApplication.class, args);

        Contact contact = new Contact();
        contact.setFirstName("John");
        contact.setLastName("Doe");
        System.out.println(contact.getFirstName());
        System.out.println(contact.getLastName());
    }


}
