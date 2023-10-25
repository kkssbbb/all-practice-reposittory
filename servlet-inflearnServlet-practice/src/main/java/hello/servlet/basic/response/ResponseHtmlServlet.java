package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHtmlServlet", urlPatterns = "/response-html")
public class ResponseHtmlServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/Html");
        response.setCharacterEncoding("utf-8");

        PrintWriter writer = response.getWriter();

        writer.println("<html>");
        writer.println("<body>");
        writer.println(" <div>이래서 서블릿을 안 쓰는구나</div>");
        writer.println("</body>");
        writer.println("</html>");
    }
}
