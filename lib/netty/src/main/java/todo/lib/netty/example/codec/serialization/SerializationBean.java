package todo.lib.netty.example.codec.serialization;

import lombok.Data;

import java.io.Serializable;

@Data
public class SerializationBean implements Serializable {

    private static final long serialVersionUID = 3235432002462705915L;
    private int age;
    private String name;
}
