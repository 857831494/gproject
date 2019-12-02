package com.gproject.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.gproject.common.config.AppinitHandler;
import com.gproject.common.utils.IDef;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class})
@ComponentScan(IDef.ROOT_PACKAGE)
@EnableMethodCache(basePackages = IDef.ROOT_PACKAGE) 
@EnableCreateCacheAnnotation
public class CenterBoot {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication application = new SpringApplication(CenterBoot.class);
        application.addListeners(new AppinitHandler());
        application.run(args);
	}

}
