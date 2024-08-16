package com.amsidh.mvc;

import com.amsidh.mvc.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@Slf4j
@SpringBootApplication
public class PersonManagementUsingGraphQlApplication implements CommandLineRunner {

    private final PersonService personService;

    public static void main(String[] args) {
        SpringApplication.run(PersonManagementUsingGraphQlApplication.class, args);
    }

    @Override
    public void run(String... args) {


    }
}
