package todo.lib.netty.example.codec.jackcon;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonMapper {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     *  create once, reuse
     * @return ObjectMapper 单例
     */
    public static ObjectMapper getInstance() {

        return MAPPER;
    }
}
