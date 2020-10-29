package todo.lib.netty.example.codec.jackcon;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class JacksonBean {

    private int age;
    private String name;
    private List<String> sons;
    private Map<String, String> addrs;
}
