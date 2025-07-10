package com.ex.beans;

import com.ex.config.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {



        var context = new AnnotationConfigApplicationContext(Config.class);

        Vehicle vehicle1 =context.getBean("bmw",Vehicle.class);
        System.out.println(vehicle1.getName());

        Vehicle vehicle2 =context.getBean("Audi",Vehicle.class);
        System.out.println(vehicle2.getName());

        Vehicle vehicle3 =context.getBean("Toyota",Vehicle.class);
        System.out.println(vehicle3.getName());


    }
}