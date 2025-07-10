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



        Supplier<Vehicle> audiSupplier = ()->{
            Vehicle audi = new Vehicle();
            audi.setName("Audi");
            return audi;
        };

        Supplier<Vehicle> bmwSupplier = ()->{
            Vehicle bmw = new Vehicle();
            bmw.setName("BMW");
            return bmw;
        };


        Random random = new Random();
        int randomNumber = random.nextInt(10);

        if (randomNumber % 2 == 0) {
            context.registerBean("audi",
                    Vehicle.class,audiSupplier);
        }else {
            context.registerBean("bmw",
                    Vehicle.class,bmwSupplier);
        }


        Vehicle bmwVeh = null;
        Vehicle audiVeh = null;

        try {
            bmwVeh =  context.getBean("bmw",Vehicle.class);
        }catch (NoSuchBeanDefinitionException e)
        {
            System.out.println(e.getMessage());
        }

        try {
            audiVeh =  context.getBean("audi",Vehicle.class);
        }catch (NoSuchBeanDefinitionException e)
        {
            System.out.println(e.getMessage());
        }

        if(audiVeh==null){
            System.out.println(bmwVeh.getName());
        }else  {
            System.out.println(audiVeh.getName());
        }

    }
}