package com.borg.vocab_app_3.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class WordItemEntity {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private int status;

    public WordItemEntity(String name, int status) {
        this.name = name;
        this.status = status;
    }

    public WordItemEntity() {
    }

    public WordItemEntity(long id, String name, int status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
