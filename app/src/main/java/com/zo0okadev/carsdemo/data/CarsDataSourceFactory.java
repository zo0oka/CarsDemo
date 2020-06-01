package com.zo0okadev.carsdemo.data;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.zo0okadev.carsdemo.interfaces.NetworkListener;
import com.zo0okadev.carsdemo.model.Car;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 01 Jun, 2020.
 * Have a nice day!
 */
public class CarsDataSourceFactory extends DataSource.Factory<Integer, Car> {

    private final NetworkListener listener;
    private CarsDataSource carsDataSource;

    public CarsDataSourceFactory(NetworkListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public DataSource<Integer, Car> create() {
        carsDataSource = new CarsDataSource(listener);
        return carsDataSource;
    }

    public void refresh() {
        carsDataSource.invalidate();
    }
}
