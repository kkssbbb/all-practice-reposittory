package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyHandlerAdapter {

    boolean supports(Object handler); //파라미터로 넘어온 핸들러를 사용할수 잇는지 참 거짓으로 판단 메서드

    ModelView handle(HttpServletRequest request , HttpServletResponse response, Object handler) throws ServletException, IOException;

}
