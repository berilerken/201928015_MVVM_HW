package com.example.a201928015_mvvm_hw.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.example.a201928015_mvvm_hw.Interfaces.CatDAO;

@Database(entities = {Model.class},version = 5)
public abstract class CatDB extends RoomDatabase {

    private static final String DATABASE_NAME = "Cat";
    public abstract CatDAO catimgDao();
    public static volatile CatDB INSTANCE = null;

    public static CatDB getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (CatDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, CatDB.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    static  class  PopulateDbAsyn extends AsyncTask<Void,Void,Void>{
        private CatDAO CatDAO;
        public PopulateDbAsyn(CatDB catDB)
        {
            CatDAO = catDB.catimgDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            CatDAO.deleteAll();
            return null;
        }
    }
    public static Callback callback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsyn(INSTANCE);
        }
    };


}