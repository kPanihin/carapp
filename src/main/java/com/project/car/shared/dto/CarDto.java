package com.project.car.shared.dto;

import com.project.car.client.domain.Car;

public class CarDto extends Dto {
    private static final long serialVersionUID = 1L;

    private String mark;
    private String model;
    private int year;
    private String typeEngine;
    private double engineCapacity;
    private String color;

    public CarDto() { }

    public CarDto (Car car){
        this.setId(car.getId());
        this.mark = car.getMark();
        this.model = car.getModel();
        this.year = car.getYear();
        this.typeEngine = car.getTypeEngine();
        this.engineCapacity = car.getEngineCapacity();
        this.color = car.getColor();
    }

    public void setAttributes(long id, String mark, String model, int year, String typeEngine, double engineCapacity, String color) {
        this.setId(id);
        this.mark = mark;
        this.model = model;
        this.year = year;
        this.typeEngine = typeEngine;
        this.engineCapacity = engineCapacity;
        this.color = color;
    }

    public String getMark() {
        return mark;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getYearString(){
        if (year == 0)
            return "Старше 1980";

        return String.valueOf(year);
    }

    public String getTypeEngine() {
        return typeEngine;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public String getCapacityEngineString(){
        if (engineCapacity < 0.7)
            return "Менее 0.7";
        if (engineCapacity > 5)
            return "Более 5.0";

        return String.valueOf(engineCapacity);
    }

    public String getColor() {
        return color;
    }

    public void copyFrom(CarDto carToCopy) {
        setId(carToCopy.getId());

        mark = carToCopy.mark;
        model = carToCopy.model;
        year = carToCopy.year;
        typeEngine = carToCopy.typeEngine;
        engineCapacity = carToCopy.engineCapacity;
        color = carToCopy.color;
    }

    @Override
    public String toString(){
        return this.getId() + " "
                + this.mark + " "
                + this.model + " "
                + this.year + " "
                + this.typeEngine + " "
                + this.engineCapacity + " "
                + this.color;
    }

}
