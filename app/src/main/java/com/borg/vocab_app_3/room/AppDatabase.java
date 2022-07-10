package com.borg.vocab_app_3.room;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {WordItemEntity.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AdapterItemDao getAdapterItemDao();
}
