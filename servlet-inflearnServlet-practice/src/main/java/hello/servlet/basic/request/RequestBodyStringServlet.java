package hello.servlet.basic.request;


import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name="requestBodyStringServlet", urlPatterns = "/request-body")
public class RequestBodyStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream inputStream = req.getInputStream();// 바디 제이터를 바이트코드로 얻어올 수 있다.
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        //스프링에서 제공하는 바이트코드 문자열로 변환
        System.out.println("messageBody = " + messageBody);
        resp.getWriter().write("확인");
    }
}
