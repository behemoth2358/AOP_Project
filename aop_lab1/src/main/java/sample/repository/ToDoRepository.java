package sample.repository;

import sample.helper.LogHelper;
import sample.manager.IDatabaseManager;
import sample.model.ToDoItem;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class ToDoRepository extends Observable {
    private IDatabaseManager databaseManager;
    private List<ToDoItem> toDoItems;

    public ToDoRepository(IDatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        loadData();
    }

    private void loadData() {
        ResultSet rs = databaseManager.executeQuery("select * from ToDoItems");
        toDoItems = new ArrayList<> ();

        try {
            while (rs.next()) {
                ToDoItem entity = map(rs);

                if (entity != null) {
                    toDoItems.add(entity);
                }
            }
        } catch (Exception e) {
            LogHelper.Instance.LogError(e);
        }
    }

    public ToDoItem map(ResultSet rs) {
        try {
            return new ToDoItem(
                    rs.getInt("id"),
                    rs.getString("val"),
                    rs.getString("date")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void add(ToDoItem i) {
        databaseManager.executeUpdate("insert into ToDoItems(val, date) values ('"+ i.getValue() +"', '" + i.getDate() +"')");
        Notify();
    }

    public void update(ToDoItem i) {
        databaseManager.executeUpdate("update ToDoItems set val = '" + i.getValue() + "' where id = " + i.getId().toString());
        Notify();
    }

    public void delete(int id) {
        databaseManager.executeUpdate("delete from ToDoItems where id = " + id);
        Notify();
    }

    public List<ToDoItem> getToDoItems() {
        loadData();
        return toDoItems;
    }

    private void Notify() {
        LogHelper.Instance.LogInfo("ToDoRepository.Notify() called!");
        setChanged();
        notifyObservers();
    }
}
