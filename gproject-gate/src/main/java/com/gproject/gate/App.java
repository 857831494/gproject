package com.gproject.gate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.gproject.common.IDef;
import com.gproject.common.config.AppinitHandler;

/**
 * Hello world!
 *
 */
@SpringBootApplication()
@ComponentScan(IDef.ROOT_PACKAGE)
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication application = new SpringApplication(App.class);
        application.addListeners(new AppinitHandler());
        application.run(args);
    }
}
