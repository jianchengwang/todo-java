package todo.java.geekbang.designpattern.behavioral.observer;

/**
 * @author jianchengwang
 * @date 2023/6/15
 */
public interface Subject {
    void registerObserver(Observer observer);
    void unregisterObserver(Observer observer);
    void notifyObservers();
}
