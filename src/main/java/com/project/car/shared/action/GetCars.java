package com.project.car.shared.action;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.Out;
import com.gwtplatform.dispatch.rpc.shared.UnsecuredActionImpl;
import com.project.car.client.domain.Car;
import com.project.car.shared.dto.CarDto;

import java.util.List;

@GenDispatch(isSecure = false, serviceName = UnsecuredActionImpl.DEFAULT_SERVICE_NAME)
public class GetCars {
    @Out(1)
    List<CarDto> cars;
}
