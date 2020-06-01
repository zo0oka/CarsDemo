package com.zo0okadev.carsdemo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 01 Jun, 2020.
 * Have a nice day!
 */
public class CarsDataSource extends PageKeyedDataSource<Integer, Car> {

    private final NetworkListener errorListener;
    private static final String TAG = CarsDataSource.class.getSimpleName();

    public CarsDataSource(NetworkListener errorListener) {
        this.errorListener = errorListener;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Car> callback) {
        ApiClient.getInstance().getCars(1).enqueue(new Callback<CarsResponse>() {
            @Override
            public void onResponse(@NotNull Call<CarsResponse> call, @NotNull Response<CarsResponse> response) {
                if (response.isSuccessful()) {
                    List<Car> cars;
                    if (response.body() != null) {
                        if (response.body().getStatus() != 0) {
                            cars = response.body().getCars();
                        } else {
                            cars = Collections.emptyList();
                        }
                    } else {
                        cars = Collections.emptyList();
                    }
                    callback.onResult(cars, null, 2);
                } else {
                    String errorString = null;
                    if (response.errorBody() != null) {
                        try {
                            errorString = response.errorBody().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    errorListener.onNetworkError(errorString);
                    Log.d(TAG, "onResponse: Error: " + errorString);
                }
            }

            @Override
            public void onFailure(@NotNull Call<CarsResponse> call, @NotNull Throwable t) {
                errorListener.onNetworkError(t.getMessage() != null ? t.getMessage() : "Unknown Error");
            }
        });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Car> callback) {
        ApiClient.getInstance().getCars(params.key).enqueue(new Callback<CarsResponse>() {
            @Override
            public void onResponse(@NotNull Call<CarsResponse> call, @NotNull Response<CarsResponse> response) {
                if (response.isSuccessful()) {
                    List<Car> cars;
                    if (response.body() != null) {
                        if (response.body().getStatus() != 0) {
                            cars = response.body().getCars();
                        } else {
                            cars = Collections.emptyList();
                        }
                    } else {
                        cars = Collections.emptyList();
                    }
                    callback.onResult(cars, params.key - 1);
                } else {
                    String errorString = null;
                    if (response.errorBody() != null) {
                        try {
                            errorString = response.errorBody().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    errorListener.onNetworkError(errorString);
                    Log.d(TAG, "onResponse: Error: " + errorString);
                }
            }

            @Override
            public void onFailure(@NotNull Call<CarsResponse> call, @NotNull Throwable t) {
                errorListener.onNetworkError(t.getMessage() != null ? t.getMessage() : "Unknown Error");
            }
        });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Car> callback) {
        ApiClient.getInstance().getCars(params.key).enqueue(new Callback<CarsResponse>() {
            @Override
            public void onResponse(@NotNull Call<CarsResponse> call, @NotNull Response<CarsResponse> response) {
                if (response.isSuccessful()) {
                    List<Car> cars;
                    if (response.body() != null) {
                        if (response.body().getStatus() != 0) {
                            cars = response.body().getCars();
                        } else {
                            cars = Collections.emptyList();
                        }
                    } else {
                        cars = Collections.emptyList();
                    }
                    callback.onResult(cars, params.key + 1);
                } else {
                    String errorString = null;
                    if (response.errorBody() != null) {
                        try {
                            errorString = response.errorBody().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    errorListener.onNetworkError(errorString);
                    Log.d(TAG, "onResponse: Error: " + errorString);
                }
            }

            @Override
            public void onFailure(@NotNull Call<CarsResponse> call, @NotNull Throwable t) {
                errorListener.onNetworkError(t.getMessage() != null ? t.getMessage() : "Unknown Error");
            }
        });
    }
}
