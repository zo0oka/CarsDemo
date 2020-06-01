package com.zo0okadev.carsdemo.model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 01 Jun, 2020.
 * Have a nice day!
 */
public class Car {

    public static final DiffUtil.ItemCallback<Car> DIFF_CALLBACK = new DiffUtil.ItemCallback<Car>() {
        @Override
        public boolean areItemsTheSame(@NonNull Car oldItem, @NonNull Car newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Car oldItem, @NonNull Car newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (id != car.id) return false;
        if (isUsed != car.isUsed) return false;
        if (!brand.equals(car.brand)) return false;
        if (!constructionYear.equals(car.constructionYear)) return false;
        return imageUrl.equals(car.imageUrl);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + brand.hashCode();
        result = 31 * result + constructionYear.hashCode();
        result = 31 * result + (isUsed ? 1 : 0);
        result = 31 * result + imageUrl.hashCode();
        return result;
    }

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("constructionYear")
    @Expose
    private String constructionYear;
    @SerializedName("isUsed")
    @Expose
    private boolean isUsed;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(String constructionYear) {
        this.constructionYear = constructionYear;
    }

    public boolean isIsUsed() {
        return isUsed;
    }

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
