package com.zo0okadev.carsdemo.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.zo0okadev.carsdemo.interfaces.NetworkListener;
import com.zo0okadev.carsdemo.model.Car;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 01 Jun, 2020.
 * Have a nice day!
 */
public class CarsRepo implements NetworkListener {

    private MutableLiveData<String> errorMutableLiveData = new MutableLiveData<>();
    private LiveData<PagedList<Car>> cars;

    public void loadCars(NetworkListener listener) {
        CarsDataSourceFactory factory = new CarsDataSourceFactory(listener);
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setMaxSize(40)
                .setPageSize(20)
                .setPrefetchDistance(10)
                .build();
        cars = new LivePagedListBuilder<>(factory, config).build();
    }

    public LiveData<PagedList<Car>> getCars() {
        return cars;
    }

    @Override
    public void onNetworkError(String error) {
        errorMutableLiveData.postValue(error);
    }

    public LiveData<String> getNetworkError() {
        return errorMutableLiveData;
    }

    public void refresh() {
        cars.getValue().getDataSource().invalidate();
    }
}
