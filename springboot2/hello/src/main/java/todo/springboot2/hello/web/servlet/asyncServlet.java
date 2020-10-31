package todo.springboot2.hello.web.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/asyncServlet", asyncSupported = true)
public class asyncServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        // 开启异步
        AsyncContext asyncContext = req.startAsync();

        asyncContext.start(() -> {

            try {
                resp.getWriter().println("async servlet");

                // 触发完成
                asyncContext.complete();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
