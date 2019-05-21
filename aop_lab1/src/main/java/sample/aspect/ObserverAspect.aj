package sample.aspect;

import sample.helper.ContainerHelper;
import sample.helper.LogHelper;

import java.util.Observable;

public aspect ObserverAspect extends Observable {
    pointcut doNotifyOnUpdate():execution(* sample.repository.ToDoRepository.update(..));
    pointcut doNotifyOnAdd():execution(* sample.repository.ToDoRepository.add(..));
    pointcut doNotifyOnDelete():execution(* sample.repository.ToDoRepository.delete(..));

    after():doNotifyOnUpdate() {
        Notify();
    }

    after():doNotifyOnAdd() {
        Notify();
    }

    after():doNotifyOnDelete() {
        Notify();
    }

    void Notify() {
        if (ContainerHelper.repositoryInstance != null) {
            LogHelper.Instance.LogError("NOTIFYING HEREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE!");
            ContainerHelper.repositoryInstance.Notify();
        }
    }
}
