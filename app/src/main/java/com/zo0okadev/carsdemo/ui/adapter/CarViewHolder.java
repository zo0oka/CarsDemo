package com.zo0okadev.carsdemo.ui.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zo0okadev.carsdemo.databinding.CarListItemBinding;
import com.zo0okadev.carsdemo.model.Car;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 01 Jun, 2020.
 * Have a nice day!
 */
public class CarViewHolder extends RecyclerView.ViewHolder {

    private CarListItemBinding binding;

    public CarViewHolder(@NonNull CarListItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bindTo(Car car) {
        binding.setCar(car);
    }
}
