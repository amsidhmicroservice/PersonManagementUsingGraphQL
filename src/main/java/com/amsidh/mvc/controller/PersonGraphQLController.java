package com.amsidh.mvc.controller;

import com.amsidh.mvc.model.PersonRequestModel;
import com.amsidh.mvc.model.PersonResponseModel;
import com.amsidh.mvc.model.FilterCriteria;
import com.amsidh.mvc.model.SortBy;
import com.amsidh.mvc.service.PersonService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Controller
public class PersonGraphQLController {

    private final PersonService personService;

    @QueryMapping
    public List<PersonResponseModel> getAllPersons(
            @Argument List<FilterCriteria> filter,
            @Argument int offset,
            @Argument int limit,
            @Argument SortBy sort) {
        return personService.allPersons(filter, offset, limit, sort);
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
