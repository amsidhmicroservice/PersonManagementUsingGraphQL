package com.amsidh.mvc.service.impl;

import com.amsidh.mvc.exception.PersonNotFoundException;
import com.amsidh.mvc.mapper.PersonMapper;
import com.amsidh.mvc.model.*;
import com.amsidh.mvc.repo.PersonEntityRepository;
import com.amsidh.mvc.repo.entity.PersonEntity;
import com.amsidh.mvc.service.PersonService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        if (personEntity.getAddressEntity() != null) {
            personEntity.getAddressEntity().setPersonEntity(personEntity);
            log.info("AddressEntity before save - city: {}, street: {}, state: {}, pinCode: {}",
                    personEntity.getAddressEntity().getCity(),
                    personEntity.getAddressEntity().getStreet(),
                    personEntity.getAddressEntity().getState(),
                    personEntity.getAddressEntity().getPinCode());
        }

        final PersonEntity savedPersonEntity = personEntityRepository.save(personEntity);
        log.info("Saved PersonEntity with ID: {}", savedPersonEntity.getPersonId());
        if (savedPersonEntity.getAddressEntity() != null) {
            log.info("Saved AddressEntity with ID: {}, city: {}, street: {}, state: {}, pinCode: {}",
                    savedPersonEntity.getAddressEntity().getAddressId(),
                    savedPersonEntity.getAddressEntity().getCity(),
                    savedPersonEntity.getAddressEntity().getStreet(),
                    savedPersonEntity.getAddressEntity().getState(),
                    savedPersonEntity.getAddressEntity().getPinCode());
        }

        final PersonResponseModel personResponseModel = PersonMapper.INSTANCE.toPersonResponseModel(savedPersonEntity);
        log.info("Saved Person {}", personResponseModel);

        return personResponseModel;
    }

    @Override
    public PersonResponseModel findPersonByPersonId(Long personId) {
        final PersonEntity personEntity = personEntityRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException(String
                        .format("Person with personId %d not found", personId)));
        final PersonResponseModel personResponseModel = PersonMapper.INSTANCE.toPersonResponseModel(personEntity);
        log.info("Person found {}", personResponseModel);
        return personResponseModel;
    }

    @Override
    public PersonResponseModel updatePerson(Long personId, PersonRequestModel personRequestModel) {
        final PersonEntity personEntity = personEntityRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException(String.format("Person with personId %d not found", personId)));
        Optional.ofNullable(personRequestModel.getFirstName()).ifPresent(personEntity::setFirstName);
        Optional.ofNullable(personRequestModel.getLastName()).ifPresent(personEntity::setLastName);
        Optional.ofNullable(personRequestModel.getEmail()).ifPresent(personEntity::setEmail);
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
    public List<PersonResponseModel> allPersons(List<FilterCriteria> filter, int offset, int limit, SortBy sort) {
        Specification<PersonEntity> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (FilterCriteria criteria : filter) {
                String key = criteria.getKey();
                FilterInput filterInput = criteria.getFilterInput();
                FilterType filterType = filterInput.getFilterType();
                List<String> filterValues = filterInput.getFilterValues();

                switch (filterType) {
                    case eq:
                        predicates.add(criteriaBuilder.equal(root.get(key), filterValues.get(0)));
                        break;
                    case nq:
                        predicates.add(criteriaBuilder.notEqual(root.get(key), filterValues.get(0)));
                        break;
                    case contains:
                        predicates.add(criteriaBuilder.like(root.get(key), "%" + filterValues.get(0) + "%"));
                        break;
                    case notContains:
                        predicates.add(criteriaBuilder.notLike(root.get(key), "%" + filterValues.get(0) + "%"));
                        break;
                    case gte:
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(key), filterValues.get(0)));
                        break;
                    case lte:
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(key), filterValues.get(0)));
                        break;
                    case eqIgnoreCase:
                        predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(root.get(key)), filterValues.get(0).toLowerCase()));
                        break;
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        if (sort != null) {
            String key = sort.getKey();
            SortOrder order = sort.getOrder();
            if (order == SortOrder.ASC) {
                return personEntityRepository.findAll(spec, org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Order.asc(key)))
                        .stream()
                        .skip(offset)
                        .limit(limit)
                        .map(PersonMapper.INSTANCE::toPersonResponseModel)
                        .toList();
            } else {
                return personEntityRepository.findAll(spec, org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Order.desc(key)))
                        .stream()
                        .skip(offset)
                        .limit(limit)
                        .map(PersonMapper.INSTANCE::toPersonResponseModel)
                        .toList();
            }
        }

        return personEntityRepository.findAll(spec)
                .stream()
                .skip(offset)
                .limit(limit)
                .map(PersonMapper.INSTANCE::toPersonResponseModel)
                .toList();
    }

    @Override
    public void deletePerson(Long personId) {
        log.info("Deleting person with personId {}", personId);
        personEntityRepository.deleteById(personId);
        log.info("Person deleted successfully");
    }
}
