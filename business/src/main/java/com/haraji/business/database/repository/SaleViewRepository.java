package com.haraji.business.database.repository;

import com.haraji.business.database.entity.SaleViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaleViewRepository extends JpaRepository<SaleViewEntity, Long> {

//    @Query(nativeQuery = true, value = "call sp_off(:cityId, :businessTypeId);", name ="sp_off" )
    @Query(nativeQuery = true, value = "call sp_off(:cityId, :businessTypeId);", name ="sp_off" )
    List<SaleViewEntity> getAllSalesByCityIdAndBusinessTypeId(@Param("cityId") Integer cityId, @Param("businessTypeId") Integer businessTypeId);

    Optional<SaleViewEntity> findBySaleId(Long saleId);

}
