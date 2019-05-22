package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.util.Callback;
import sample.aspect.ObserverAspect;
import sample.helper.ContainerHelper;
import sample.helper.LogHelper;
import sample.model.ToDoItem;
import sample.repository.ToDoRepository;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class StartController implements Observer, Initializable {
    private ToDoRepository repository;
    private ToDoItem selectedToDoItem;

    public StartController() {
        this.repository = ContainerHelper.repositoryInstance;
        this.repository.addObserver(this);
    }

    @FXML public Button refreshBtn;
    @FXML public Button saveBtn;
    @FXML public Button newBtn;
    @FXML public Button deleteBtn;
    @FXML public TextArea idTextArea;
    @FXML public TextArea valueTextArea;
    @FXML public TextArea dateTextArea;
    @FXML public ListView<ToDoItem> itemsListView;

    @FXML public void refreshBtn_onAction() {
        loadData();
    }

    @FXML public void saveBtn_onAction() {
//        LogHelper.Instance.LogInfo("StartController.saveBtn_onAction called!");

        if (selectedToDoItem.getId() == null) {
            repository.add(new ToDoItem(valueTextArea.getText()));
        } else {
            repository.update(new ToDoItem(selectedToDoItem.getId(), valueTextArea.getText(), selectedToDoItem.getDate()));
        }

        clearSelection();
    }

    @FXML public void deleteBtn_onAction() {
//        LogHelper.Instance.LogInfo("StartController.deleteBtn_onAction called!");

        repository.delete(selectedToDoItem.getId());

        clearSelection();
        selectedToDoItem = new ToDoItem();
    }

    @FXML public void newBtn_onAction() {
//        LogHelper.Instance.LogInfo("StartController.newBtn_onAction called!");

        clearSelection();
        selectedToDoItem = new ToDoItem();
    }

    @FXML public void itemsListView_onAction() {
//        LogHelper.Instance.LogInfo("StartController.itemListView_onAction called!");

        selectedToDoItem = itemsListView.getSelectionModel().getSelectedItem();

        idTextArea.setText(String.valueOf(selectedToDoItem.getId()));
        valueTextArea.setText(selectedToDoItem.getValue());
        dateTextArea.setText(selectedToDoItem.getDate());
    }


    @Override
    public void update(Observable o, Object arg) {
//        LogHelper.Instance.LogInfo("StartController.update called!");

        loadData();
    }

    private void clearSelection() {
        selectedToDoItem = null;
        idTextArea.clear();
        valueTextArea.clear();
        dateTextArea.clear();
    }

    private void loadData() {
//        LogHelper.Instance.LogInfo("StartController.loadData called!");
        itemsListView.setItems(FXCollections.observableList(repository.getToDoItems()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        LogHelper.Instance.LogInfo("StartController.initialize called!");
        itemsListView.setCellFactory(new Callback<ListView<ToDoItem>, ListCell<ToDoItem>>(){
            @Override
            public ListCell<ToDoItem> call(ListView<ToDoItem> p) {

                ListCell<ToDoItem> cell = new ListCell<ToDoItem>(){

                    @Override
                    protected void updateItem(ToDoItem t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.toString());
                        }
                    }

                };

                return cell;
            }});

        loadData();
    }
}
