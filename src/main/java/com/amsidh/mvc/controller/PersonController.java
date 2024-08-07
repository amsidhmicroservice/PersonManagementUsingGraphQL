package com.amsidh.mvc.controller;

import com.amsidh.mvc.model.PersonRequestModel;
import com.amsidh.mvc.model.PersonResponseModel;
import com.amsidh.mvc.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Controller
public class PersonController {

    private final PersonService personService;

    @QueryMapping
    public List<PersonResponseModel> getAllPersons(@Argument String nameFilter) {
        return personService.allPersons();
    }

    @QueryMapping
    public PersonResponseModel findByPersonId(@Argument Long personId) {
        return personService.findPersonByPersonId(personId);
    }


    @MutationMapping
    public PersonResponseModel createPerson(@Argument PersonRequestModel personRequestModel) {
        return personService.savePerson(personRequestModel);
    }

    @MutationMapping
    public PersonResponseModel updatePerson(@Argument Long personId, @Argument PersonRequestModel personRequestModel) {
        return personService.updatePerson(personId, personRequestModel);
    }

    @MutationMapping
    public String deletePerson(@Argument Long personId) {
        personService.deletePerson(personId);
        return String.format("Person with personId %d is deleted successfully", personId);
    }
}
