package com.zo0okadev.carsdemo.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;

import com.zo0okadev.carsdemo.R;
import com.zo0okadev.carsdemo.databinding.CarListItemBinding;
import com.zo0okadev.carsdemo.model.Car;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 01 Jun, 2020.
 * Have a nice day!
 */
public class CarsPagedRecyclerAdapter extends PagedListAdapter<Car, CarViewHolder> {

    protected CarsPagedRecyclerAdapter() {
        super(Car.DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CarListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.car_list_item, parent, false);
        return new CarViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }
}
