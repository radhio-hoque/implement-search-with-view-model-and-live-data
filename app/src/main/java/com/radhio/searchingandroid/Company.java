package com.radhio.searchingandroid;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Azmia Hoque Radhio on 8/23/2021.
 */
public class Company {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("logo_path")
    @Expose
    private Object logoPath;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("origin_country")
    @Expose
    private String originCountry;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(Object logoPath) {
        this.logoPath = logoPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public static final DiffUtil.ItemCallback<Company> CALLBACK=new DiffUtil.ItemCallback<Company>() {
        @Override
        public boolean areItemsTheSame(Company oldItem, Company newItem) {
            return oldItem.id.equals(newItem.id);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Company oldItem, @NonNull Company newItem) {
            return true;
        }
    };
}
