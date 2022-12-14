package com.ybkim.springstudy.springboot.web;

import com.ybkim.springstudy.springboot.config.auth.LoginUser;
import com.ybkim.springstudy.springboot.config.auth.dto.SessionUser;
import com.ybkim.springstudy.springboot.service.PostsService;
import com.ybkim.springstudy.springboot.web.dto.PostsReponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){ //Model: 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
        //여기서는 postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달한다.
        //@LoginUser SessionUser user : rlwhsdp httpSession.getAttribute("user")로 가져오던 세션 정보 값이 개선됨
        model.addAttribute("posts", postsService.findAllDesc());
        if(user != null){
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsReponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
