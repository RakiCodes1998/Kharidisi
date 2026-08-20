package com.kharidisi.kharidisibackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KharidisiBackendApplication {

    public static void main(String[] args) {

        System.out.println("DATABASE_URL = " + System.getenv("DATABASE_URL"));
        System.out.println("DB_USERNAME = " + System.getenv("DB_USERNAME"));
        System.out.println("DB_PASSWORD present = " + (System.getenv("DB_PASSWORD") != null));

        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("POSTGRES DRIVER FOUND ✅");
        } catch (ClassNotFoundException e) {
            System.out.println("POSTGRES DRIVER NOT FOUND ❌");

        }



        SpringApplication.run(KharidisiBackendApplication.class, args);
    }


}
