package com.amsidh.mvc.repo;

import com.amsidh.mvc.repo.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressEntityRepository extends JpaRepository<AddressEntity, Long> {
}
