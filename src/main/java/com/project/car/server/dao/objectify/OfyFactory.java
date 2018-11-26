package com.project.car.server.dao.objectify;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.googlecode.objectify.ObjectifyFactory;
import com.project.car.client.domain.Car;

public class OfyFactory extends ObjectifyFactory{
    private Injector injector;

    @Inject
    public OfyFactory(Injector injector) {
        this.injector = injector;

        long time = System.currentTimeMillis();

        this.register(Car.class);

        long mills = System.currentTimeMillis() - time;
        System.out.println("Registration took " + mills + " millis");
    }

    @Override
    public <T> T construct(Class<T> type) {
        return injector.getInstance(type);
    }
}
