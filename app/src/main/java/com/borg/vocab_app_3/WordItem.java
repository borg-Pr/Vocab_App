package com.borg.vocab_app_3;


public class WordItem {
    private long id;
    private String name;
    private boolean isSelected;

    public WordItem(String name, boolean isSelected) {
        this.name = name;
        this.isSelected = isSelected;
    }

    public WordItem(long id, String name, boolean isSelected) {
        this.id = id;
        this.name = name;
        this.isSelected = isSelected;
    }

    public WordItem(long id, String name) {
        this.id = id;
        this.name = name;
        this.isSelected = false;
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

    public boolean getIsSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
