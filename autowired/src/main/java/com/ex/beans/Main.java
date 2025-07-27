package com.ex.beans;

import com.ex.config.Config;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Random;
import java.util.function.Supplier;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        var context = new AnnotationConfigApplicationContext(Config.class);

        Person person = context.getBean(Person.class);
        System.out.println(person.getVehicle().getName());


    }
}