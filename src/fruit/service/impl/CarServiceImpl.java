package fruit.service.impl;

import fruit.base.BaseDao;

import fruit.base.BaseServiceImpl;
import fruit.mapper.CarMapper;
import fruit.mapper.ItemOrderMapper;
import fruit.po.Car;
import fruit.po.ItemOrder;
import fruit.service.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CarServiceImpl extends BaseServiceImpl<Car> implements CarService {
    @Autowired
    private CarMapper carMapper;

    @Override
    public BaseDao<Car> getBaseDao() {
        return carMapper;
    }
}
