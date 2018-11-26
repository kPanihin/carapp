package com.project.car.client.gin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.gwtplatform.dispatch.rpc.client.gin.RpcDispatchAsyncModule;
import com.project.car.client.application.ApplicationModule;
import com.project.car.client.place.NameTokens;
import com.project.car.client.resources.ResourceLoader;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

public class ClientModule extends AbstractGinModule {
    @Override
    protected void configure() {
        install(new DefaultModule
                .Builder()
                .defaultPlace(NameTokens.HOME)
                .errorPlace(NameTokens.HOME)
                .unauthorizedPlace(NameTokens.HOME)
                .build());
        install(new RpcDispatchAsyncModule());
        install(new ApplicationModule());

        bind(ResourceLoader.class).asEagerSingleton();
    }
}
