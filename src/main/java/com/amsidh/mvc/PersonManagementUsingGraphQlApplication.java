package com.amsidh.mvc;

import com.amsidh.mvc.model.AddressRequestModel;
import com.amsidh.mvc.model.PersonRequestModel;
import com.amsidh.mvc.model.PersonResponseModel;
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
        AddressRequestModel pune = AddressRequestModel.builder().city("Pune").street("D Y Patil").state("MH").pinCode(412105L).build();
        AddressRequestModel bijapur = AddressRequestModel.builder().city("Shirnal").street("Kannur Road").state("KA").pinCode(586119L).build();

        PersonRequestModel amisdh = PersonRequestModel.builder().name("Amsidh").age(42).addressRequestModel(pune).build();

        PersonRequestModel anjali = PersonRequestModel.builder().name("Anjali").age(38).addressRequestModel(bijapur).build();


        final PersonResponseModel personResponseModel = personService.savePerson(amisdh);
        log.info("Person Response Model {}", personResponseModel);

        final PersonResponseModel personResponseModel1 = personService.savePerson(anjali);
        log.info("Person Response Model1 {}", personResponseModel1);


    }
}
