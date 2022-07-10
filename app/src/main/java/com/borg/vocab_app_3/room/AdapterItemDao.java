package com.borg.vocab_app_3.room;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AdapterItemDao {

    @Insert
    void insert(WordItemEntity wordItemEntity);

    @Update
    void update(WordItemEntity wordItemEntity);

    @Delete
    void delete(WordItemEntity wordItemEntity);

    @Query("SELECT * FROM WordItemEntity")
    List<WordItemEntity> getAll();

    @Query("SELECT * FROM WordItemEntity WHERE status = :status")
    List<WordItemEntity> getAllByStatus(int status);

    @Query("SELECT * FROM WordItemEntity WHERE id = :id")
    WordItemEntity getById(long id);

    @Insert
    long insertWithReturn(WordItemEntity wordItemEntity);

    @Query("DELETE FROM WordItemEntity WHERE id = :id")
    void deleteById(long id);
}
