package cn.jianchengwang.todo.springboot2.hello.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        System.out.println("this is MyFilter,url :"+httpServletRequest.getRequestURI());
        chain.doFilter(request, response);
    }
}
