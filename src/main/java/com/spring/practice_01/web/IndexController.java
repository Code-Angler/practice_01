package com.spring.practice_01.web;

import com.spring.practice_01.config.auth.Dto.SessionUser;
import com.spring.practice_01.config.auth.LoginUser;
import com.spring.practice_01.service.posts.PostsService;
import com.spring.practice_01.web.dto.PostsResponseDto;
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
    public String index(Model model ,@LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());
        //SessionUser user = (SessionUser) httpSession.getAttribute("user"); -> 파라미터로 받는 어노테이션을 설정 한 후, 뺐음.
        if(user != null){
            model.addAttribute("userName",user.getName());
        }
        return "index";
        //여기서부터 View resolver가 처리함.
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);

        return "posts-update";
    }
}
