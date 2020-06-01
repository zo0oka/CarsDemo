package com.zo0okadev.carsdemo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 01 Jun, 2020.
 * Have a nice day!
 */
public class CarsViewModel extends ViewModel {

    private CarsRepo carsRepo;

    public CarsViewModel() {
        carsRepo = new CarsRepo();
    }

    public LiveData<PagedList<Car>> getCars() {
        return carsRepo.getCars();
    }

    public void loadCars(NetworkListener listener) {
        carsRepo.loadCars(listener);
    }

    public void refresh() {
        carsRepo.refresh();
    }
}
