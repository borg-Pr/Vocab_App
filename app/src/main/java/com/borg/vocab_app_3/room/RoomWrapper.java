package com.borg.vocab_app_3.room;

import android.content.Context;

import androidx.room.Room;

public class RoomWrapper {

    private static AppDatabase database = null;

    public static AppDatabase getDatabase(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context, AppDatabase.class, "database")
                    // позволяет выполнять запросы к БД в главном потоке
                    .allowMainThreadQueries()
                    // удаляет текущую базу если её схема не совпадает с новой схемой
                    // после изменения какого-нибудь Entity, либо после создания нового Entity
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }
}
