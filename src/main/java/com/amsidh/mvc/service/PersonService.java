package com.amsidh.mvc.service;

import com.amsidh.mvc.model.FilterCriteria;
import com.amsidh.mvc.model.PersonRequestModel;
import com.amsidh.mvc.model.PersonResponseModel;
import com.amsidh.mvc.model.SortBy;

import java.util.List;

public interface PersonService {
    PersonResponseModel savePerson(PersonRequestModel personRequestModel);

    PersonResponseModel findPersonByPersonId(Long personId);

    PersonResponseModel updatePerson(Long personId, PersonRequestModel personRequestModel);

    List<PersonResponseModel> allPersons(List<FilterCriteria> filter, int offset, int limit, SortBy sort);

    void deletePerson(Long personId);
}
