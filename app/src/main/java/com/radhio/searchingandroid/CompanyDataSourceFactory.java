package com.radhio.searchingandroid;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

/**
 * Created by Azmia Hoque Radhio on 8/23/2021.
 */
public class CompanyDataSourceFactory extends DataSource.Factory{

    private final String query;
    public CompanyDataSourceFactory(String query) {
        this.query = query;
    }

    @NonNull
    @Override
    public DataSource create() {
        CompanyRepository paymentRepository = new CompanyRepository(query);
        MutableLiveData<CompanyRepository> clientPaymentLiveData = new MutableLiveData<>();

        clientPaymentLiveData.postValue(paymentRepository);
        return paymentRepository;
    }
}
