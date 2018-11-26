package com.project.car.shared.dto;

public class CarDto extends Dto {
    private static final long serialVersionUID = 1L;

    private String mark;
    private String model;
    private int year;
    private String typeEngine;
    private float engineCapacity;
    private String color;

    public CarDto() { }

    public void setAttributes(long id, String mark, String model, int year, String typeEngine, float engineCapacity, String color) {
        setId(id);
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

    public String getTypeEngine() {
        return typeEngine;
    }

    public float getEngineCapacity() {
        return engineCapacity;
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
}
