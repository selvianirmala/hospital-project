package org.example;

import org.example.Config.BeanConfiguration;
import org.example.Controller.MainController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new  AnnotationConfigApplicationContext(BeanConfiguration.class);
        MainController mainController = ctx.getBean(MainController.class);
        mainController.run();
    }
}