package com.project.car.client.application.home;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.RangeChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.project.car.client.domain.Car;
import com.project.car.client.widget.cartable.CarTableWidgetPresenter;
import com.project.car.shared.dto.CarDto;
import org.gwtbootstrap3.client.ui.Pagination;
import org.gwtbootstrap3.client.ui.gwt.DataGrid;
import org.gwtbootstrap3.extras.select.client.ui.Option;
import org.gwtbootstrap3.extras.select.client.ui.Select;
import org.gwtbootstrap3.extras.select.client.ui.event.HideEvent;

import java.util.ArrayList;
import java.util.List;

public class HomeView extends ViewWithUiHandlers<HomePresenter> implements HomePresenter.MyView {
    interface Binder extends UiBinder<Widget, HomeView> {
    }

    @UiField
    Select markSelect;

    @UiField
    Select modelSelect;

    @UiField
    Select yearSelect;

    @UiField
    Select colorSelect;

    @UiField
    Select typeEngineSelect;

    @UiField
    Select engineCapacitySelect;

    @UiField
    Label label;

    @UiField
    FlexTable flexTable;

    @Inject
    HomeView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

        fillYearSelect();
        modelSelect.setEnabled(false);
    }

    @Override
    public void fillModelSelectWithMark(ArrayList<String> modelsWithMark) {
        modelSelect.clear();

        for (String s : modelsWithMark) {
            Option option = new Option();
            option.setText(s);

            modelSelect.add(option);
        }

        modelSelect.refresh();
    }

    @Override
    public void fillYearSelect(){
        for (int year = 1980; year <= 2018; year++){
            Option option = new Option();
            option.setText(String.valueOf(year));

            yearSelect.add(option);
        }

        yearSelect.refresh();
    }

    @UiHandler("markSelect")
    public void onMarkSelectHidden(HideEvent event) {
        if (!modelSelect.isEnabled())
            modelSelect.setEnabled(true);

        Option selectedItem = markSelect.getSelectedItem();

        String markCar = selectedItem.getValue();

        getUiHandlers().getModelsCar(markCar);
    }

    @UiHandler("sendCar")
    public void onSend(ClickEvent event) {
        getUiHandlers().onSend(markSelect.getSelectedItem(),   modelSelect.getSelectedItem(),
                yearSelect.getSelectedItem(), typeEngineSelect.getSelectedItem(),
                engineCapacitySelect.getSelectedItem(), colorSelect.getSelectedItem());
    }

    @UiHandler("getCars")
    public void onGet(ClickEvent event){
        initHeader();
        getUiHandlers().onGet();
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


    @Override
    public void showSelectedItems(Car car){
        label.setText(car.toString());
    }
}
