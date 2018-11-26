package com.project.car.client.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;
import com.project.car.shared.DatastoreObject;

@Entity
public class Car extends DatastoreObject {
    @Index
    private String mark;
    @Index
    private String model;
    @Index
    private int year;
    private String typeEngine;
    private float engineCapacity;
    private String color;

    public Car() {

    }

    public Car(String mark, String model, int year, String typeEngine, float engineCapacity, String color) {
        this.mark = mark;
        this.model = model;
        this.year = year;
        this.typeEngine = typeEngine;
        this.engineCapacity = engineCapacity;
        this.color = color;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setEngineCapacity(float engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public void setTypeEngine(String typeEngine) {
        this.typeEngine = typeEngine;
    }

    public String getTypeEngine() {
        return typeEngine;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString(){
        return this.mark + " "
                + this.model + " "
                + this.year + " "
                + this.typeEngine + " "
                + this.engineCapacity + " "
                + this.color;
    }
}
