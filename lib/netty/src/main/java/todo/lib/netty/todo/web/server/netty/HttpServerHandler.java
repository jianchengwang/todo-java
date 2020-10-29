package todo.lib.netty.todo.web.server.netty;

import todo.lib.netty.todo.web.server.Const;
import todo.lib.netty.todo.web.server.action.IWorkAction;
import todo.lib.netty.todo.web.server.context.wrapper.Rp;
import todo.lib.netty.todo.web.server.context.wrapper.Rq;
import todo.lib.netty.todo.web.server.context.WebContext;
import todo.lib.netty.todo.web.server.route.Route;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;

public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private static final byte[] SUCCESS = { 'S', 'U', 'C', 'C', 'E', 'S', 'S' };
    private static final byte[] NOTFOUND = { 'N', 'O', 'T', 'F', 'O', 'U', 'N', 'D' };

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {

        byte[] responseMsg = SUCCESS;

        System.out.println("uri:" + msg.uri());
        System.out.println("method:" + msg.method().name());
        System.out.println("headers:");msg.headers().forEach(h -> System.out.println(h));

        WebContext.init(
            new Rq(msg),
            new Rp()
        );

        String uri = msg.uri();
        if(uri.indexOf(".do") != -1) {
            uri = uri.substring(0, msg.uri().indexOf(".do"));

            if(uri.indexOf("/") == 0) {
                uri = uri.substring(1, uri.length());
            }

            if(Const.ROUTE_MAP.containsKey(uri)) {

                Route route = Const.ROUTE_MAP.get(uri);

                IWorkAction workAction = route.getWorkAction().newInstance();
                workAction.execute(WebContext.me());

            } else {
                WebContext.me().getRp().error("NOTFOUND");
            }

        } else {
            WebContext.me().getRp().error("NOTFOUND");
        }

        ChannelFuture f = ctx.writeAndFlush(WebContext.me().getRp().getRaw());
        f.addListener(ChannelFutureListener.CLOSE);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
