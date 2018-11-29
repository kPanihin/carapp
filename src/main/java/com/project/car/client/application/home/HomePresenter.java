package com.project.car.client.application.home;

import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.project.car.client.application.ApplicationPresenter;
import com.project.car.client.domain.CarModels;
import com.project.car.client.dispatch.AsyncCallbackImpl;
import com.project.car.client.domain.Car;
import com.project.car.client.place.NameTokens;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.project.car.server.dao.CarDao;
import com.project.car.shared.action.GetCarsAction;
import com.project.car.shared.action.GetCarsResult;
import com.project.car.shared.action.SetCarAction;
import com.project.car.shared.action.SetCarResult;
import com.project.car.shared.dto.CarDto;
import org.gwtbootstrap3.extras.select.client.ui.Option;

import java.util.ArrayList;
import java.util.List;


public class HomePresenter extends Presenter<HomePresenter.MyView, HomePresenter.MyProxy> implements HomeUiHandlers {
    interface MyView extends View, HasUiHandlers<HomePresenter> {
        void fillModelSelectWithMark(ArrayList<String> modelsWithMark);
        void fillYearSelect();

    }

    @ProxyStandard
    @NameToken(NameTokens.HOME)
    interface MyProxy extends ProxyPlace<HomePresenter> {
    }

    private ArrayList<CarModels> cars;
    private Car car;
    
    private final DispatchAsync dispatcher;
    private final PlaceManager placeManager;

    @Inject
    HomePresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            DispatchAsync dispatcher,
            PlaceManager placeManager) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);

        this.dispatcher = dispatcher;
        this.placeManager = placeManager;

        getView().setUiHandlers(this);
    }

    @Override
    protected void onBind() {
        super.onBind();

        initCars();
    }

    @Override
    public void getModelsCar(String markCar) {
        ArrayList<String> models = new ArrayList<String>();

        for (CarModels carModels : cars) {
            if (carModels.getMark() == markCar) {
                models = carModels.getModels();
                break;
            }
        }

        getView().fillModelSelectWithMark(models);
    }

    private void initCars() {
        cars = new ArrayList<>();

        CarModels carModels = new CarModels("ВАЗ", new ArrayList<String>() {{
            add("2101");
            add("2102");
            add("2103");
            add("2104");
            add("2105");
            add("2106");
            add("2107");
            add("2108");
            add("2109");
            add("21010");
            add("21011");
            add("21012");
            add("21013");
            add("21014");
            add("21015");
            add("Нива");
            add("Веста");
            add("Гранта");
            add("Калина");
        }});
        cars.add(carModels);

        carModels = new CarModels("BMW", new ArrayList<String>() {{
            add("1 series");
            add("2 series");
            add("3 series");
            add("4 series");
            add("5 series");
            add("6 series");
            add("7 series");
            add("X1");
            add("X2");
            add("X3");
            add("X4");
            add("X5");
            add("X6");
            add("X7");
        }});
        cars.add(carModels);

        carModels = new CarModels("Тойота", new ArrayList<String>() {{
            add("Karina");
            add("Land Cruiser 100");
            add("Land Cruiser 200");
            add("Mark 1");
            add("Mark 2");
            add("Celica");
        }});
        cars.add(carModels);

        carModels = new CarModels("Москвич", new ArrayList<String>() {{
            add("408");
            add("410");
            add("412");
            add("2140");
            add("2141");
            add("Святогор");
        }});
        cars.add(carModels);
    }

    @Override
    public void onSend(Option markOption, Option modelOption, Option yearOption, Option typeEngineOption, Option capacityEngineOption, Option colorOption) {
        car = new Car();

        car.setMark(markOption.getValue());
        car.setModel(modelOption.getValue());

        String year = yearOption.getValue();
        if (year.equals("Старше 1980"))
            car.setYear(0);
        else car.setYear(Integer.valueOf(year));

        car.setTypeEngine(typeEngineOption.getValue());

        String capacityEngine = capacityEngineOption.getValue();
        switch (capacityEngine) {
            case "Менее 0.7":
                car.setEngineCapacity(0);
                break;
            case "Более 5.0":
                car.setEngineCapacity(6);
                break;
            default:
                car.setEngineCapacity(Float.valueOf(capacityEngine));
                break;
        }

        car.setColor(colorOption.getValue());

        dispatcher.execute(new SetCarAction(car), new AsyncCallbackImpl<SetCarResult>() {
            @Override
            protected void onCustomSuccess(SetCarResult result) {

            }
        });
    }

    @Override
    public void onShow(){
        PlaceRequest placeRequest = new PlaceRequest.Builder()
                .nameToken(NameTokens.TABLE)
                .build();
        placeManager.revealPlace(placeRequest);
    }
}
