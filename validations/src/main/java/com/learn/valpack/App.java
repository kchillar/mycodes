package com.learn.valpack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class App 
{

    public static void main(String[] args)
    {
    	System.out.println("SriRama SriRama SriRama");
    	log.info("Sri Rama");
    	SpringApplication.run(App.class, args);
    }	
    

}
