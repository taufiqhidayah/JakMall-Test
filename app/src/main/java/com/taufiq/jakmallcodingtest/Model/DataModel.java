package com.taufiq.jakmallcodingtest.Model;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataModel {
    @SerializedName("id")
    private Integer id;
    private String joke;
    private String kategori;

    public DataModel(Integer id, String joke, String kategori) {
        this.id = id;
        this.joke = joke;
        this.kategori = kategori;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}
