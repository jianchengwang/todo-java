package cn.jianchengwang.todo.lib.netty.todo.web.server.context.wrapper;

import cn.jianchengwang.todo.lib.netty.todo.web.server.context.param.ParamMap;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import lombok.Data;

import java.util.List;

@Data
public class Rq {

    private FullHttpRequest fullReq;
    private ParamMap paramMap;

    public Rq(FullHttpRequest fullReq) throws Exception {
        this.fullReq = fullReq;

        buildParamMap();
    }

    private void buildParamMap() throws Exception {

        HttpMethod method = fullReq.method();

        ParamMap paramMap = new ParamMap();

        if (HttpMethod.GET == method) {
            // 是GET请求
            QueryStringDecoder decoder = new QueryStringDecoder(fullReq.uri());
            decoder.parameters().entrySet().forEach( entry -> {
                // entry.getValue()是一个List, 只取第一个元素
                paramMap.put(entry.getKey(), entry.getValue().get(0));
            });
        } else if (HttpMethod.POST == method) {
            // 是POST请求
            HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(fullReq);
            decoder.offer(fullReq);

            List<InterfaceHttpData> parmList = decoder.getBodyHttpDatas();

            for (InterfaceHttpData parm : parmList) {

                Attribute data = (Attribute) parm;
                paramMap.put(data.getName(), data.getValue());
            }

        } else {
            // 不支持其它方法
            throw new Exception(""); // 这是个自定义的异常, 可删掉这一行
        }

        this.paramMap = paramMap;
    }

}
