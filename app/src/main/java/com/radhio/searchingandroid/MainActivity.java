package com.radhio.searchingandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.radhio.searchingandroid.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private CompanyViewModel companyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        companyViewModel = new ViewModelProvider(this).get(CompanyViewModel.class);
        companyViewModel.getMoviesPagedList().observe(this, this::setPaymentLayout);
        companyViewModel.filterCompanyName.setValue("%" + "a" + "%");

        mainBinding.searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                companyViewModel.filterCompanyName.setValue("%" + s.toString() + "%");
            }
        });
    }
    private void setPaymentLayout(PagedList<Company> moviesFromLiveData) {
        CompanyAdapter companyAdapter = new CompanyAdapter(this);
        companyAdapter.submitList(moviesFromLiveData);
        mainBinding.recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        mainBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        mainBinding.recyclerView.setAdapter(companyAdapter);
    }
}