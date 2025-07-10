package com.ex.beans;

import com.ex.config.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {



        var context = new AnnotationConfigApplicationContext(Config.class);
        Vehicle vehicle =context.getBean(Vehicle.class);
        System.out.println(vehicle.getName());

        String hello =context.getBean(String.class);
        System.out.println("String from spring context:"+hello);

        Integer integer = context.getBean(Integer.class);
        System.out.println("Integer from spring context:"+integer);
    }
}