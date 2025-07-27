package org.example.requetparam;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

@SpringBootApplication
public class RequetParamApplication {

    public static void main(String[] args) {
        SpringApplication.run(RequetParamApplication.class, args);



    }


}
