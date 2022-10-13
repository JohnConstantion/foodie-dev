package com.john;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author johnconstantine
 * @see ComponentScan 扫描所有的包以及相关的组件包
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.john", "org.n3r.idworker"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
