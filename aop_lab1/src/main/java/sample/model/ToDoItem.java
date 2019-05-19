package sample.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ToDoItem {
    private Integer id;
    private String value;
    private String date;

    public ToDoItem() {}

    public ToDoItem(String value) {
        this.value = value;
        this.date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public ToDoItem(int id, String value, String date) {
        this(value);
        this.id = id;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return this.date + ": " + this.value;
    }
}
