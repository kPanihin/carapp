package com.project.car.client.application.table;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.project.car.client.application.ApplicationPresenter;
import com.project.car.client.application.home.HomePresenter;
import com.project.car.client.dispatch.AsyncCallbackImpl;
import com.project.car.client.domain.Car;
import com.project.car.client.domain.CarModels;
import com.project.car.client.place.NameTokens;
import com.project.car.shared.action.GetCarsAction;
import com.project.car.shared.action.GetCarsResult;
import com.project.car.shared.action.SetCarAction;
import com.project.car.shared.action.SetCarResult;
import com.project.car.shared.dto.CarDto;
import org.gwtbootstrap3.extras.select.client.ui.Option;

import java.util.ArrayList;
import java.util.List;


public class TablePresenter extends Presenter<TablePresenter.MyView, TablePresenter.MyProxy> implements TableUiHandlers {
    interface MyView extends View, HasUiHandlers<TablePresenter> {
        void showCars(List<CarDto> cars);
    }

    @ProxyStandard
    @NameToken(NameTokens.TABLE)
    interface MyProxy extends ProxyPlace<TablePresenter> {
    }

    private final DispatchAsync dispatcher;
    private final PlaceManager placeManager;

    @Inject
    TablePresenter(
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
    public void onReveal(){
        loadCars();
    }

    public void loadCars(){
        dispatcher.execute(new GetCarsAction(), new AsyncCallbackImpl<GetCarsResult>() {
            @Override
            protected void onCustomSuccess(GetCarsResult result) {
                getView().showCars(result.getCars());
            }
        });
    }

    @Override
    public void onBack(){
        PlaceRequest placeRequest = new PlaceRequest.Builder()
                .nameToken(NameTokens.HOME)
                .build();
        placeManager.revealPlace(placeRequest);
    }
}
