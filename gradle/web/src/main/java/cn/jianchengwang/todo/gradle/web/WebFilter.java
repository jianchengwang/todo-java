package cn.jianchengwang.todo.gradle.web;

import cn.jianchengwang.todo.gradle.dao.TodoItemDao;
import cn.jianchengwang.todo.gradle.model.TodoItem;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class WebFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        TodoItemDao todoItemDao = new TodoItemDao();

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String url = httpServletRequest.getRequestURL().toString();
        String queryString = httpServletRequest.getQueryString();

        String result = "";

        String idStr = httpServletRequest.getParameter("id");
        Long id = 0L;
        if(idStr != null) {
            id = Long.parseLong(idStr);
        }
        String name = httpServletRequest.getParameter("name");

        TodoItem todoItem = new TodoItem(id, name);

        if(url.contains("all.do")) {
            List<TodoItem> todoItemList = todoItemDao.all();
            for(TodoItem todoItem1: todoItemList) {
                result += todoItem1.getName() + "<br/>";
            }
        } else if(url.contains("add.do")) {
            todoItemDao.add(todoItem);
            result = "添加成功";
        } else if(url.contains("remove.do")) {
            todoItemDao.remove(todoItem);
            result = "移除成功";
        } else {
            result = "请求无效";
        }

        StringBuilder sb = new StringBuilder()
                .append("请求路径:").append(url).append("<br/>")
                .append("请求参数:").append(queryString).append("<br/>")
                .append("请求结果:").append(result).append(" ");

        ((HttpServletResponse) response).setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("请求路径:" + sb.toString());

    }

    @Override
    public void destroy() {

    }
}
