package com.example.myapplication;

public class ToDoModel {
    private String id;
    private String itemDataText;
    private boolean done;

    public ToDoModel() {
    }

    public ToDoModel(String id, String itemDataText, boolean done) {
        this.id = id;
        this.itemDataText = itemDataText;
        this.done = done;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemDataText() {
        return itemDataText;
    }

    public void setItemDataText(String itemDataText) {
        this.itemDataText = itemDataText;
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
