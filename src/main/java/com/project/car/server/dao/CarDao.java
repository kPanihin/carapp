package com.project.car.server.dao;

import com.project.car.client.domain.Car;

public class CarDao extends BaseDao<Car> {
    public CarDao() {
        super(Car.class);
    }

    protected CarDao(Class<Car> clazz) {
        super(clazz);
    }
}
