package com.project.car.client.domain;

import java.util.ArrayList;

public class CarModels{
    private String mark;
    private ArrayList<String> models;

    public CarModels(String mark, ArrayList<String> models){
        this.mark = mark;
        this.models = models;
    }

    public void setMark(String mark){
        this.mark = mark;
    }

    public void setModels(ArrayList<String> models){
        this.models = models;
    }

    public String getMark(){
        return mark;
    }

    public ArrayList<String> getModels(){
        return models;
    }
}
