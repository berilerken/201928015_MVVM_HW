package com.example.a201928015_mvvm_hw.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.a201928015_mvvm_hw.Database.Model;
import com.example.a201928015_mvvm_hw.Repository.Repository;

import java.util.List;

public class CatViewModel extends AndroidViewModel {
    private Repository repository;
    public LiveData<List<Model>> getAllCats;

    public CatViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);
        getAllCats=repository.getAllCats();
    }
    public LiveData<List<Model>> getAllCats()
    {
        return getAllCats;
    }
}
