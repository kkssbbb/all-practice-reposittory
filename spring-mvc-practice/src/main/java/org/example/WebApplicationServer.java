package org.example;


import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class WebApplicationServer  {

    //WebApplicationServer 클래스 안에서의 로그 출력을 위함
    private static final Logger logger = LoggerFactory.getLogger(WebApplicationServer.class
    );


    public static void main(String[] args) throws Exception {
        String webAppDirLocation = "webapps/";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        //경로설정 = 톰켓의 루트 디렉토리 설정 = 톰켓 실행하면 루트 디렉토리를 먼저 찾아서 실행
        tomcat.addWebapp("/", new File(webAppDirLocation).getAbsolutePath());
        //설정된 웹앱 기본 경료 디렉토리경로보기 위한 log
        logger.info("configuring app with basedir = {}", new File(webAppDirLocation).getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();
    }
}