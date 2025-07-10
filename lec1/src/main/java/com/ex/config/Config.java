package com.ex.config;

import com.ex.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.ex.beans")
public class Config {

    @Bean
    Vehicle vehicle(){
        var veh = new Vehicle();
        veh.setName("bmw 8");
        return veh;
    }

    @Bean
    String name(){
        return "Hello World";
    }

    @Bean
    Integer age(){
        return 1;
    }
}
