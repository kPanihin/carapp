package com.project.car.client.application.table;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.*;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.project.car.client.place.NameTokens;
import com.project.car.shared.dto.CarDto;
import org.gwtbootstrap3.client.ui.AnchorListItem;
import org.gwtbootstrap3.client.ui.Breadcrumbs;
import org.gwtbootstrap3.client.ui.NavPills;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

import javax.inject.Inject;
import java.util.*;

public class TableView extends ViewWithUiHandlers<TablePresenter> implements TablePresenter.MyView {
    interface Binder extends UiBinder<Widget, TableView> {
    }

    @UiField
    SimplePanel panel;

    @UiField
    NavPills floorsNav;

    @UiField
    Breadcrumbs sectionsBread;

    private CellTable<CarDto> cellTable;


    @Inject
    TableView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
        //initHeader();

        initFloorsNav();
    }


    public void initFloorsNav() {
        floorsNav.clear();

        for (int floorNum = 1; floorNum < 11; floorNum++) {

            final AnchorListItem floorItem = new AnchorListItem("Этаж " + floorNum);

            final int finalFloorNum = floorNum;
            floorItem.addClickHandler(new ClickHandler() {

                @Override
                public void onClick(ClickEvent event) {
                    displaySections(finalFloorNum);

                    //floorItem.setActive(true);

                    floorItem.setStyleName("background-color: red");

                    //floorItem.setStyleName("active");


                    //Window.alert(String.valueOf(breedIndex));
                }
            });
            floorsNav.add(floorItem);
        }
    }

    public void displaySections(int floorNum){
        sectionsBread.clear();
            for (int block = 1; block < 4; block++) {
                final AnchorListItem item = new AnchorListItem("Секция " + block);
                item.addClickHandler(new ClickHandler() {

                    @Override
                    public void onClick(ClickEvent event) {
                        item.setActive(true);
                    }
                });
                sectionsBread.add(item);
            }
    }


    @UiHandler("back")
    public void onBack(ClickEvent event) {
        getUiHandlers().onBack();
    }

    public void initHeader() {
        cellTable = new CellTable<CarDto>();

        TextColumn<CarDto> markColumn = new TextColumn<CarDto>() {
            @Override
            public String getValue(CarDto car) {
                return car.getMark();
            }
        };

        TextColumn<CarDto> modelColumn = new TextColumn<CarDto>() {
            @Override
            public String getValue(CarDto car) {
                return car.getModel();
            }
        };

        TextColumn<CarDto> yearColumn = new TextColumn<CarDto>() {
            @Override
            public String getValue(CarDto carDto) {
                return carDto.getYearString();
            }
        };

        TextColumn<CarDto> typeEngineColumn = new TextColumn<CarDto>() {
            @Override
            public String getValue(CarDto car) {
                return car.getTypeEngine();
            }
        };

        TextColumn<CarDto> capacityEngineColumn = new TextColumn<CarDto>() {
            @Override
            public String getValue(CarDto carDto) {
                return carDto.getCapacityEngineString();
            }
        };

        TextColumn<CarDto> colorColumn = new TextColumn<CarDto>() {
            @Override
            public String getValue(CarDto car) {
                return car.getColor();
            }
        };

        cellTable.addColumn(markColumn, "Марка");
        cellTable.addColumn(modelColumn, "Модель");
        cellTable.addColumn(yearColumn, "Год");
        cellTable.addColumn(typeEngineColumn, "Тип мотора");
        cellTable.addColumn(capacityEngineColumn, "Объем мотора");
        cellTable.addColumn(colorColumn, "Цвет");
    }

    @Override
    public void showCars(List<CarDto> cars) {

        cellTable.setRowCount(cars.size(), true);

        cellTable.setRowData(cars);

        panel.add(cellTable);
    }
}
