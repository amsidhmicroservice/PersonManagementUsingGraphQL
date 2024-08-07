package com.amsidh.mvc.repo;

import com.amsidh.mvc.repo.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonEntityRepository extends JpaRepository<PersonEntity, Long> {
}
