package com.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AOP {

    @Before("execution(public void myData())")
    public void printStart() {
        System.out.println("✅ AOP: قبل تنفيذ دالة myData");
    }
}
