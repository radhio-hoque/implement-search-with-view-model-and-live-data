package com.radhio.searchingandroid;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Azmia Hoque Radhio on 7/12/2021.
 */
public class CompanyViewModel extends ViewModel {

    public MutableLiveData<String> filterCompanyName ;

    public CompanyViewModel() {
        filterCompanyName = new MutableLiveData<>();
    }

    public LiveData<PagedList<Company>> getMoviesPagedList() {
        PagedList.Config config=(new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();

        Executor executor = Executors.newFixedThreadPool(5);


        return Transformations.switchMap(new DebouncedLiveData<>(filterCompanyName, 400), outputLive -> {
            if (outputLive == null || outputLive.equals("") || outputLive.equals("%%")) {
                //check if the current value is empty load all data else search
                synchronized (this) {
                    return new LivePagedListBuilder<Long, Company>(
                            new CompanyDataSourceFactory("a"), config)
                            .setFetchExecutor(executor)
                            .build();
                }
            } else {
                return new LivePagedListBuilder<Long, Company>(
                        new CompanyDataSourceFactory("%" + outputLive + "%"), config)
                        .setFetchExecutor(executor)
                        .build();
            }
        });
    }
}