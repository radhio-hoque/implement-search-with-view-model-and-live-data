package com.radhio.searchingandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.radhio.searchingandroid.databinding.CompanyItemBinding;

/**
 * Created by Azmia Hoque Radhio on 8/22/2021.
 */
public class CompanyAdapter extends PagedListAdapter<Company, CompanyAdapter.MovieViewHolder> {

    private Context context;
    public CompanyAdapter(Context context) {
        super(Company.CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CompanyItemBinding movieListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.company_item, parent, false);
        return new MovieViewHolder(movieListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Company movie = getItem(position);
        assert movie != null;
        holder.movieListItemBinding.setCompany(movie);
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        private final CompanyItemBinding movieListItemBinding;
        public MovieViewHolder(@NonNull CompanyItemBinding movieListItemBinding) {
            super(movieListItemBinding.getRoot());
            this.movieListItemBinding = movieListItemBinding;
        }
    }
}
