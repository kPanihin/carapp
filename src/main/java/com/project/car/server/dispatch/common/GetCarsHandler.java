package com.project.car.server.dispatch.common;


import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;
import com.project.car.client.domain.Car;
import com.project.car.server.dao.CarDao;
import com.project.car.server.dispatch.MyAbstractActionHandler;
import com.project.car.shared.action.GetCarsAction;
import com.project.car.shared.action.GetCarsResult;
import com.project.car.shared.dto.CarDto;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class GetCarsHandler extends MyAbstractActionHandler<GetCarsAction, GetCarsResult> {
    @Inject
    public GetCarsHandler() {
        super(GetCarsAction.class);
    }

    @Override
    public GetCarsResult execute(GetCarsAction action, ExecutionContext context) throws ActionException {
        List<Car> cars = new CarDao().listAll();
        List<CarDto> carDtos = new ArrayList<CarDto>();

        for (Car car : cars){
            carDtos.add(new CarDto(car));
        }

        return new GetCarsResult(carDtos);
    }
}
