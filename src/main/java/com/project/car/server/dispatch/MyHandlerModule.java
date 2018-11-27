package com.project.car.server.dispatch;

import com.gwtplatform.dispatch.rpc.server.guice.HandlerModule;
import com.project.car.server.dispatch.common.GetCarsHandler;
import com.project.car.server.dispatch.common.SetCarHandler;
import com.project.car.shared.action.GetCarsAction;
import com.project.car.shared.action.GetCarsResult;
import com.project.car.shared.action.SetCarAction;

public class MyHandlerModule extends HandlerModule {
    @Override
    protected void configureHandlers() {

        bindHandler(SetCarAction.class, SetCarHandler.class);
        bindHandler(GetCarsAction.class, GetCarsHandler.class);
    }
}
