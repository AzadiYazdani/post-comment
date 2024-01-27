package com.sale.baseinfo.database.repository;

import com.sale.baseinfo.database.entity.BusinessTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusinessTypeRepository extends JpaRepository<BusinessTypeEntity, Long> {

    Optional<BusinessTypeEntity> findById(Long id);

    List<BusinessTypeEntity> findAllByTitleContains(String titleValue);
}
