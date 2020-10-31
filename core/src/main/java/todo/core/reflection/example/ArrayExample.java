package todo.core.reflection.example;

import java.lang.reflect.Array;

public class ArrayExample {

    public static void main(String[] args) throws ClassNotFoundException {

        int[] intArray = (int[]) Array.newInstance(int.class, 3);

        Array.set(intArray, 0, 123);
        Array.set(intArray, 1, 456);
        Array.set(intArray, 2, 789);

        System.out.println("intArray[0] = " + Array.get(intArray, 0));
        System.out.println("intArray[1] = " + Array.get(intArray, 1));
        System.out.println("intArray[2] = " + Array.get(intArray, 2));


        Class stringArrayClass = String[].class;
        Class intArray1 = Class.forName("[I");
        Class stringArrayClass1 = Class.forName("[Ljava.lang.String;");

        String[] strings = new String[3];
        Class stringArrayClass3 = strings.getClass();
        Class stringArrayComponentType = stringArrayClass.getComponentType();
        System.out.println(stringArrayComponentType);


    }
}
