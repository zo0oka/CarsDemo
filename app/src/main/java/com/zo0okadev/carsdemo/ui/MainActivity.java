package com.zo0okadev.carsdemo.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zo0okadev.carsdemo.R;
import com.zo0okadev.carsdemo.databinding.ActivityMainBinding;
import com.zo0okadev.carsdemo.interfaces.NetworkListener;
import com.zo0okadev.carsdemo.ui.adapter.CarsPagedRecyclerAdapter;
import com.zo0okadev.carsdemo.viewModel.CarsViewModel;

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