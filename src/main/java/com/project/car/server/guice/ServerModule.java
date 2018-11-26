package com.project.car.server.guice;

import com.google.inject.AbstractModule;
import com.project.car.server.dao.objectify.OfyService;
import com.project.car.server.dispatch.MyHandlerModule;

public class ServerModule extends AbstractModule {
    @Override
    protected void configure() {
        requestStaticInjection(OfyService.class);

        install(new MyHandlerModule());
    }
}
