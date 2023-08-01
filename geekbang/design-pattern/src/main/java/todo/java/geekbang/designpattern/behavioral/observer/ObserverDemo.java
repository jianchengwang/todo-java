package todo.java.geekbang.designpattern.behavioral.observer;

/**
 * @author jianchengwang
 * @date 2023/6/15
 */
public class ObserverDemo {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();

        TemperatureDisplay temperatureDisplay1 = new TemperatureDisplay();
        TemperatureDisplay temperatureDisplay2 = new TemperatureDisplay();

        weatherStation.registerObserver(temperatureDisplay1);
        weatherStation.registerObserver(temperatureDisplay2);

        weatherStation.setTemperature(25);    // update weather temperature, notify observers

        weatherStation.unregisterObserver(temperatureDisplay2);

        weatherStation.setTemperature(30);    // update weather temperature, notify observers
    }
}
