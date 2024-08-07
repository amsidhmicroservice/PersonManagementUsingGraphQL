package com.amsidh.mvc.mapper;

import com.amsidh.mvc.model.PersonRequestModel;
import com.amsidh.mvc.model.PersonResponseModel;
import com.amsidh.mvc.repo.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "personId", ignore = true)
        // Ignoring the ID as it's auto-generated
    PersonEntity toPersonEntity(PersonRequestModel personRequestModel);

    PersonRequestModel toPersonRequestModel(PersonEntity personEntity);

    PersonResponseModel toPersonResponseModel(PersonEntity personEntity);

    List<PersonResponseModel> toPersonResponseModelList(List<PersonEntity> personEntities);
}
