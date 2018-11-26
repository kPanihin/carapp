package com.project.car.server.guice;

import com.gargoylesoftware.htmlunit.WebClient;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;
import com.googlecode.objectify.ObjectifyFilter;
import com.gwtplatform.dispatch.rpc.server.guice.DispatchServiceImpl;
import com.gwtplatform.dispatch.rpc.shared.ActionImpl;

public class MyServletModule extends ServletModule {
    @Override
    protected void configureServlets() {
        serve("/" + ActionImpl.DEFAULT_SERVICE_NAME  + "*").with(DispatchServiceImpl.class);

        // Objectify filter
        bind(ObjectifyFilter.class).in(Singleton.class);
        filter("/*").through(ObjectifyFilter.class);
    }

    @Singleton
    @Provides
    WebClient getWebClient() {
        return new WebClient();
    }
}

