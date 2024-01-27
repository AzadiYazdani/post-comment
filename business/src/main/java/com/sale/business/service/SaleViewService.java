package com.sale.business.service;

import com.sale.business.model.SaleView;

import javax.validation.constraints.Min;
import java.util.List;

public interface SaleViewService {

    List<SaleView> getAllSalesByCityAndBusinessType(int id_city, int id_business_type);

    SaleView getById(@Min(1) long id);


}
