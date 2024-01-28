package com.haraji.business.service;

import com.haraji.business.database.entity.SaleViewEntity;
import com.haraji.business.database.repository.SaleViewRepository;
import com.haraji.business.exception.SaleNotFoundException;
import com.haraji.business.mapper.SaleMapper;
import com.haraji.business.model.SaleView;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleViewServiceImpl implements SaleViewService {

    private final SaleMapper saleMapper;

    private final SaleViewRepository saleViewRepository;

    public SaleViewServiceImpl(SaleMapper saleMapper, SaleViewRepository saleViewRepository) {
        this.saleMapper = saleMapper;
        this.saleViewRepository = saleViewRepository;
    }

    public List<SaleView> getAllSalesByCityAndBusinessType(int cityId, int businessTypeId) {

        List<SaleViewEntity> entities = saleViewRepository.getAllSalesByCityIdAndBusinessTypeId(cityId, businessTypeId);

        return saleMapper.toModelList(entities);
    }

    @Override
    public SaleView getById(long id) {
        Optional<SaleViewEntity> entity = saleViewRepository.findBySaleId(id);
        if (entity.isPresent())
            return saleMapper.toModel(entity.get());
        else
            throw new SaleNotFoundException(id);
    }
}
