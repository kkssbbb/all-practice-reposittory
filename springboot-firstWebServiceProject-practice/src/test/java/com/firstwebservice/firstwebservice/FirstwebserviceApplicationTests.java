package com.firstwebservice.firstwebservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.firstwebservice.firstwebservice.web.HelloController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class) //여러 테스트 어노테이션중 , web(spring Mvc)에 집중 할 수 있는 어노테이션

//@SpringBootTest
class FirstwebserviceApplicationTests {
	@Autowired //스프링이 관리하는 빈(Bean)을 주입 받는다.

	//웹 API를 테스트 할 때 사용 , 스프링 MVC테서트의 시작점 ,이클래스를 통해 HTTP GET,POST등에 대한 API테스트를 할 수 있다/
	private MockMvc mvc;

	@Test
	public void hello가_리턴된다() throws Exception{
		String hello ="hello";

		mvc.perform(get("/hello")) //MockMvc를 통해 /hello 주소로 HTTP GET 요청
				.andExpect(status().isOk()) //mvc.perform의 결과를 검증
				.andExpect(content().string(hello)); //mvc.perform의 결괄ㄹ 검증
	}
}
