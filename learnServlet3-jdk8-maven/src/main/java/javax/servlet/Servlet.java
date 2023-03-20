package javax.servlet;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Servlet {
    public void init();

    public void service(HttpServletRequest request, HttpServletResponse response)
        throws IOException;

    public void destroy();
}
