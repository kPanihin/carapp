package com.project.car.client.application.home;

import com.gwtplatform.mvp.client.UiHandlers;
import org.gwtbootstrap3.extras.select.client.ui.Option;

public interface HomeUiHandlers extends UiHandlers {
    void onSend(Option mark, Option model, Option year, Option typeEngine, Option capacityEngine, Option color); // The method we want the Presenter to implement.
    void onGet();

    void getModelsCar(String markCar);
}
