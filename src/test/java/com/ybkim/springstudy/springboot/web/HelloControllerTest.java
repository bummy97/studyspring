/*
* 테스트코드 작성하기
*/

package com.ybkim.springstudy.springboot.web;

import com.ybkim.springstudy.springboot.config.auth.SecurityConfig;
import com.ybkim.springstudy.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) //테스트를 진행할때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킴, 여기서는 SpringRunner라는 스프링 실행자 실행
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        }
) //여러 스프링 어노테이션 중, Web에 집중할 수 있는 어노테이션
public class HelloControllerTest {

    @Autowired //스프링티 관리하는 빈을 주입 받는다.
    private MockMvc mvc; //웹api를 테스트할 때 사용한다. http method의 get, post등에 대한 api 테스트를 진행할 수 있다.

    @WithMockUser(roles = "USER")
    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string(hello));

        /*
        mvc.perform: /hello 주소로 http get 요청을 한다.

        .andExpect : mcv.perform의 결과를 검증, 우리가 흔히 알고 있는 200, 400, 500 등의 상태를 검증 여기서는 200인지 아닌지 검증

        .andExpect : 결과를 검증, 여기서는 hello를 리턴하기에 이 값이 맞는지 검증
         */
    }
    @WithMockUser(roles = "USER")
    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 10000;

        mvc.perform(get("/hello/dto")
                        .param("name", name).
                        param("amount", String.valueOf(amount))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));

        /*
        param : API 테스트할 때 사용될 요청 파라미터를 설정함, 값은 String만 허용하며 숫자나 날짜 등의 데이터도 문자열로 변경해야함

        jsonPath : JSON 응답값을 필드별로 검증할 수 있는 메소드, $를 기준으로 필드명을 명시, 여기서는 name, amount를 검증
         */
    }
}
