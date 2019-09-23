package com.gproject.gate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.gproject.common.config.AppinitHandler;
import com.gproject.common.utils.IDef;

/**
 * Hello world!
 *
 */
@SpringBootApplication()
@ComponentScan(IDef.ROOT_PACKAGE)
@EnableMethodCache(basePackages = IDef.ROOT_PACKAGE) 
@EnableCreateCacheAnnotation
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication application = new SpringApplication(App.class);
        application.addListeners(new AppinitHandler());
        application.run(args);
    }
}
