package sample.repository;

import sample.helper.ContainerHelper;
import sample.helper.LogHelper;
import sample.manager.IRepository;
import sample.manager.ToDoItemDBManager;
import sample.model.ToDoItem;
import java.util.List;
import java.util.Observable;

public class ToDoRepository extends Observable {
    private IRepository<ToDoItem> databaseManager;
    private List<ToDoItem> toDoItems;

    public ToDoRepository() {
        this.databaseManager = ContainerHelper.itemDBManagerInstance;
        loadData();
    }

    private void loadData() {
        toDoItems = databaseManager.executeQuery("select * from ToDoItems");
    }

    public void add(ToDoItem i) {
        databaseManager.executeUpdate("insert into ToDoItems(val, date) values ('"+ i.getValue() +"', '" + i.getDate() +"')");
//        Notify();
    }

    public void update(ToDoItem i) {
        databaseManager.executeUpdate("update ToDoItems set val = '" + i.getValue() + "' where id = " + i.getId().toString());
//        Notify();
    }

    public void delete(int id) {
        databaseManager.executeUpdate("delete from ToDoItems where id = " + id);
//        Notify();
    }

    public List<ToDoItem> getToDoItems() {
        loadData();
        return toDoItems;
    }

    public void Notify() {
        LogHelper.Instance.LogInfo("ToDoRepository.Notify() called!");
        setChanged();
        notifyObservers();
    }
}
