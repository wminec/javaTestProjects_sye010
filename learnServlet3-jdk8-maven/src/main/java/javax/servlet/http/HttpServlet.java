package javax.servlet.http;

import java.io.IOException;

import javax.servlet.Servlet;

public class HttpServlet implements Servlet {
    @Override
    public void init() {

    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if ("GET".equals(request.getMethod())) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }

    @Override
    public void destroy() { }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException { }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException { }
}
