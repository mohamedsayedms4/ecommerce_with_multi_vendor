package com.ex.config;

import com.ex.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.ex.beans")
public class Config {

    @Bean(name = "bmw")
    Vehicle vehicle1(){
        var veh = new Vehicle();
        veh.setName("bmw 8");
        return veh;
    }


    @Bean(name = "Audi")
    Vehicle vehicle2(){
        var veh = new Vehicle();
        veh.setName("Audi 8");
        return veh;
    }

    @Bean(name = "Toyota")
    Vehicle vehicle3(){
        var veh = new Vehicle();
        veh.setName("Toyota 8");
        return veh;
    }


}
