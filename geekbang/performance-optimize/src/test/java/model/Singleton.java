package model;

import coding.TestSerializable;

import java.io.Serializable;

/**
 * @author jianchengwang
 * @date 2023/4/18
 */
public class Singleton implements Serializable {

    private final static Singleton singleInstance = new Singleton();

    private Singleton(){}

    public static Singleton getInstance(){
        return singleInstance;
    }
}
