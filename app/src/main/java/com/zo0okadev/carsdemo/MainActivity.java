package com.zo0okadev.carsdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.zo0okadev.carsdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements NetworkListener, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private CarsViewModel viewModel;
    private CarsPagedRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(CarsViewModel.class);

        binding.swipeToRefreshView.setRefreshing(true);

        binding.carsRecyclerView.setHasFixedSize(true);
        adapter = new CarsPagedRecyclerAdapter();
        binding.carsRecyclerView.setAdapter(adapter);
        binding.swipeToRefreshView.setOnRefreshListener(this);

        loadCars();
        viewModel.getCars().observe(this, cars -> {
            adapter.submitList(cars);
            binding.swipeToRefreshView.setRefreshing(false);
        });
    }

    private void loadCars() {
        viewModel.loadCars(this);
    }

    @Override
    public void onNetworkError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        Log.d(TAG, "onRefresh: ");
        viewModel.refresh();
        loadCars();
    }
}