package com.amsidh.mvc.service;

import com.amsidh.mvc.model.PersonRequestModel;
import com.amsidh.mvc.model.PersonResponseModel;

import java.util.List;

public interface PersonService {
    PersonResponseModel savePerson(PersonRequestModel personRequestModel);

    PersonResponseModel findPersonByPersonId(Long personId);

    PersonResponseModel updatePerson(Long personId, PersonRequestModel personRequestModel);

    List<PersonResponseModel> allPersons();

    void deletePerson(Long personId);

}
