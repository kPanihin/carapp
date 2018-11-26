package com.project.car.server.dispatch.common;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;
import com.project.car.client.domain.Car;
import com.project.car.server.dao.CarDao;
import com.project.car.server.dispatch.MyAbstractActionHandler;
import com.project.car.shared.action.SetCarAction;
import com.project.car.shared.action.SetCarResult;

public class SetCarHandler extends MyAbstractActionHandler<SetCarAction, SetCarResult> {

    @Inject
    public SetCarHandler() {
        super(SetCarAction.class);
    }

    @Override
    public SetCarResult execute(SetCarAction action, ExecutionContext context) throws ActionException {
        Car car = action.getCar();

        CarDao carDao = new CarDao();

        return new SetCarResult(carDao.saveAndReturn(car));
    }
}
