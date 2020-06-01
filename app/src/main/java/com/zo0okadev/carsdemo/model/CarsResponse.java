package com.zo0okadev.carsdemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 01 Jun, 2020.
 * Have a nice day!
 */
public class CarsResponse {
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("data")
    @Expose
    private List<Car> cars = null;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

}
