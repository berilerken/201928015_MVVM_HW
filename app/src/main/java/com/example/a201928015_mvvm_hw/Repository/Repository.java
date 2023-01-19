package com.example.a201928015_mvvm_hw.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.a201928015_mvvm_hw.Interfaces.CatDAO;
import com.example.a201928015_mvvm_hw.Database.CatDB;
import com.example.a201928015_mvvm_hw.Database.Model;

import java.util.List;

public class Repository {
    public com.example.a201928015_mvvm_hw.Interfaces.CatDAO CatDAO;
    public LiveData<List<Model>> getAllCats;
    private CatDB database;

    public Repository(Application application){
        database= CatDB.getInstance(application);
        CatDAO =database.catimgDao();
        getAllCats= CatDAO.getcats();

    }

    public void insert(List<Model> cats){
        new InsertAsyncTask(CatDAO).execute(cats);

    }

    public LiveData<List<Model>> getAllCats(){
        return getAllCats;
    }
    private static class InsertAsyncTask extends AsyncTask<List<Model>,Void,Void>{
        private CatDAO CatDAO;

        public InsertAsyncTask(CatDAO catDao)
        {
            this.CatDAO =catDao;
        }
        @Override
        protected Void doInBackground(List<Model>... lists) {
            CatDAO.insert(lists[0]);
            return null;
        }
    }

}