package com.amsidh.mvc;

import com.amsidh.mvc.model.PersonRequestModel;
import com.amsidh.mvc.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@SpringBootApplication
public class PersonManagementUsingGraphQlApplication implements CommandLineRunner {

    private final PersonService personService;

    public static void main(String[] args) {
        SpringApplication.run(PersonManagementUsingGraphQlApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Loading initial to database");
        getPersonData().forEach(personService::savePerson);

    }

    private static List<PersonRequestModel> getPersonData() {
        return Arrays.asList(
                PersonRequestModel.builder()
                        .name("Amsidh")
                        .age(40)
                        .city("Pune")
                        .street("D Y Patil Road")
                        .state("MH")
                        .pinCode(412105L)
                        .build(),
                PersonRequestModel.builder()
                        .name("Aditya")
                        .age(10)
                        .city("Pune")
                        .street("Pride World City")
                        .state("MH")
                        .pinCode(412105L)
                        .build(),
                PersonRequestModel.builder()
                        .name("Anjali")
                        .age(37)
                        .city("Pune")
                        .street("Charholi BK")
                        .state("MH")
                        .pinCode(412105L)
                        .build(),
                PersonRequestModel.builder()
                        .name("Adithi")
                        .age(14)
                        .city("Pune")
                        .street("Long Island")
                        .state("MH")
                        .pinCode(412105L)
                        .build()
        );
    }
}
