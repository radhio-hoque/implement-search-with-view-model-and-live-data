package com.radhio.searchingandroid;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

/**
 * Created by Azmia Hoque Radhio on 8/24/2021.
 */
public class DebouncedLiveData<T> extends MediatorLiveData<T> {

    private final LiveData<T> mSource;
    private final int mDuration;
    private final Runnable debounceRunnable = new Runnable() {
        @Override
        public void run() {
            DebouncedLiveData.this.postValue(mSource.getValue());
        }
    };
    private final Handler handler = new Handler();

    public DebouncedLiveData(LiveData<T> source, int duration) {
        this.mSource = source;
        this.mDuration = duration;

        this.addSource(mSource, t -> {
            handler.removeCallbacks(debounceRunnable);
            handler.postDelayed(debounceRunnable, mDuration);
        });
    }
}