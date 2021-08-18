package net.xiahuajie.english_dog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class EnglishDogApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnglishDogApplication.class, args);
    }

}
