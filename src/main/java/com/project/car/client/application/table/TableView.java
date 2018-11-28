package com.project.car.client.application.table;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.project.car.client.domain.Car;
import com.project.car.shared.dto.CarDto;
import org.gwtbootstrap3.extras.select.client.ui.Option;
import org.gwtbootstrap3.extras.select.client.ui.Select;
import org.gwtbootstrap3.extras.select.client.ui.event.HideEvent;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TableView extends ViewWithUiHandlers<TablePresenter> implements TablePresenter.MyView {
    interface Binder extends UiBinder<Widget, TableView> {
    }

    @UiField
    FlexTable flexTable;

    @Inject
    TableView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
        initHeader();
    }

    @UiHandler("back")
    public void onBack(ClickEvent event){
        getUiHandlers().onBack();
    }

    public void initHeader(){
        flexTable.setText(0,0,"Марка");
        flexTable.setText(0,1,"Модель");
        flexTable.setText(0,2,"Год");
        flexTable.setText(0,3,"Тип двигателя");
        flexTable.setText(0,4,"Объем двигателя");
        flexTable.setText(0,5,"Цвет");
    }

    @Override
    public void showCars(List<CarDto> cars){
        int i = 1;
        for(CarDto car: cars){
            flexTable.setText(i, 0, car.getMark());
            flexTable.setText(i, 1, car.getModel());
            flexTable.setText(i, 2, Integer.toString(car.getYear()));
            flexTable.setText(i, 3, car.getTypeEngine());
            flexTable.setText(i, 4, Double.toString(car.getEngineCapacity()));
            flexTable.setText(i, 5, car.getColor());
            i++;
        }
    }
}
