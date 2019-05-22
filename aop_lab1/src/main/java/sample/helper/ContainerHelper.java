package sample.helper;

import sample.manager.ToDoItemDBManager;
import sample.repository.ToDoRepository;

public class ContainerHelper {
    public static ToDoItemDBManager itemDBManagerInstance = new ToDoItemDBManager("xpyacxfd", "xpyacxfd", "RDiHLKNCYmZ7yOKOPY3yZIBAf1ueqU6u");
    public static ToDoRepository repositoryInstance = new ToDoRepository();
}
