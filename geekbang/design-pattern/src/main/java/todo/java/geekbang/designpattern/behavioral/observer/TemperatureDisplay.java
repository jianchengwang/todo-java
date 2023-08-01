package todo.java.geekbang.designpattern.behavioral.observer;

/**
 * @author jianchengwang
 * @date 2023/6/15
 */
public class TemperatureDisplay implements Observer {
    @Override
    public void update(int temperature) {
        System.out.println("Temperature Display: Current temperature is " + temperature + " degrees Celsius.");
    }
}

