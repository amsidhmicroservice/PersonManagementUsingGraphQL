package com.amsidh.mvc.service.impl;

import com.amsidh.mvc.exception.PersonNotFoundException;
import com.amsidh.mvc.mapper.PersonMapper;
import com.amsidh.mvc.model.PersonRequestModel;
import com.amsidh.mvc.model.PersonResponseModel;
import com.amsidh.mvc.repo.PersonEntityRepository;
import com.amsidh.mvc.repo.entity.PersonEntity;
import com.amsidh.mvc.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

    private final PersonEntityRepository personEntityRepository;

    @Override
    public PersonResponseModel savePerson(PersonRequestModel personRequestModel) {
        final PersonEntity personEntity = PersonMapper.INSTANCE.toPersonEntity(personRequestModel);
        final PersonEntity savedPersonEntity = personEntityRepository.save(personEntity);
        final PersonResponseModel personResponseModel = PersonMapper.INSTANCE.toPersonResponseModel(savedPersonEntity);
        log.info("Saved Person {}", personResponseModel);
        return personResponseModel;
    }

    @Override
    public PersonResponseModel findPersonByPersonId(Long personId) {
        final PersonEntity personEntity = personEntityRepository.findById(personId).orElseThrow(() -> new PersonNotFoundException(String.format("Person with personId %d not found", personId)));
        final PersonResponseModel personResponseModel = PersonMapper.INSTANCE.toPersonResponseModel(personEntity);
        log.info("Person found {}", personResponseModel);
        return personResponseModel;
    }

    @Override
    public PersonResponseModel updatePerson(Long personId, PersonRequestModel personRequestModel) {
        final PersonEntity personEntity = personEntityRepository.findById(personId).orElseThrow(() -> new PersonNotFoundException(String.format("Person with personId %d not found", personId)));
        Optional.ofNullable(personRequestModel.getName()).ifPresent(personEntity::setName);
        Optional.ofNullable(personRequestModel.getAge()).ifPresent(personEntity::setAge);
        Optional.ofNullable(personRequestModel.getAddressRequestModel().getCity()).ifPresent(city -> personEntity.getAddressEntity().setCity(city));
        Optional.ofNullable(personRequestModel.getAddressRequestModel().getStreet()).ifPresent(street -> personEntity.getAddressEntity().setStreet(street));
        Optional.ofNullable(personRequestModel.getAddressRequestModel().getState()).ifPresent(state -> personEntity.getAddressEntity().setState(state));
        Optional.ofNullable(personRequestModel.getAddressRequestModel().getPinCode()).ifPresent(pincode -> personEntity.getAddressEntity().setPinCode(pincode));
        final PersonEntity updatedPersonEntity = personEntityRepository.saveAndFlush(personEntity);
        final PersonResponseModel personResponseModel = PersonMapper.INSTANCE.toPersonResponseModel(updatedPersonEntity);
        log.info("Person updated {}", personResponseModel);
        return personResponseModel;
    }

    @Override
    public List<PersonResponseModel> allPersons() {
        final List<PersonEntity> entities = personEntityRepository.findAll();
        final List<PersonResponseModel> personResponseModelList = PersonMapper.INSTANCE.toPersonResponseModelList(entities);
        log.info("Total person are {}", personResponseModelList.size());
        return personResponseModelList;
    }

    @Override
    public void deletePerson(Long personId) {
        log.info("Deleting person with personId {}", personId);
        personEntityRepository.deleteById(personId);
        log.info("Person deleted successfully");
    }
}
