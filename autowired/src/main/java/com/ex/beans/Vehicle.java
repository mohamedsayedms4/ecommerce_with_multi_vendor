package com.ex.beans;


import org.springframework.stereotype.Component;

@Component
public class Vehicle {

    private String name = "AUDI";


    public Vehicle() {
        System.out.println("Creating Vehicle");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void print(){
        System.out.println("Car");
    }
}
