package com.example.demo;

import com.example.demo.service.server.NettyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@RestController
@SpringBootApplication

public class Demo3Application implements CommandLineRunner {

    @Autowired
    private NettyServer nettyServer;

    public static void main(String[] args) {
        SpringApplication.run(Demo3Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        nettyServer.start();
    }

}
