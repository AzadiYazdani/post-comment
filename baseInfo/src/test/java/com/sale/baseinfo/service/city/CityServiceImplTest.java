package com.sale.baseinfo.service.city;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest(classes = CityServiceImpl.class)
@RunWith(SpringRunner.class)
class CityServiceImplTest {

    @Autowired
    CityService cityService;

    @Test
    public void getById() {
    }
}