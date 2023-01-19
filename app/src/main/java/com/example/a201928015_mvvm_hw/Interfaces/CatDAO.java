package com.example.a201928015_mvvm_hw.Interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.a201928015_mvvm_hw.Database.Model;

import java.util.List;

@Dao
public interface CatDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Model> cats);

    @Query("SELECT DISTINCT * FROM catimg")
    LiveData<List<Model>>  getcats();

    @Query("DELETE FROM catimg")
    void deleteAll();
}
