package todo.core.reflection.example;

import todo.core.reflection.model.MyAnnotation;
import todo.core.reflection.model.Person;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationExample {

    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException {

        Class c = Person.class;
        Annotation[] typeAnnotations = c.getAnnotations();
        for(Annotation annotation : typeAnnotations){
            if(annotation instanceof MyAnnotation){
                MyAnnotation myAnnotation = (MyAnnotation) annotation;
                System.out.println("name: " + myAnnotation.name());
                System.out.println("value: " + myAnnotation.value());
            }
        }

        Method annotationMethod = c.getMethod("annotationMethod");
        Annotation methodAnnotation = annotationMethod.getAnnotation(MyAnnotation.class);

        Field field = c.getField("name");
        Annotation fieldAnnotation = field.getAnnotation(MyAnnotation.class);

        Method method = c.getMethod("setName", String.class);
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        Class[] parameterTypes = method.getParameterTypes();
        int i=0;
        for(Annotation[] annotations : parameterAnnotations){
            Class parameterType = parameterTypes[i++];

            for(Annotation annotation : annotations){
                if(annotation instanceof MyAnnotation){
                    MyAnnotation myAnnotation = (MyAnnotation) annotation;
                    System.out.println("param: " + parameterType.getName());
                    System.out.println("name : " + myAnnotation.name());
                    System.out.println("value: " + myAnnotation.value());
                }
            }
        }

    }
}
