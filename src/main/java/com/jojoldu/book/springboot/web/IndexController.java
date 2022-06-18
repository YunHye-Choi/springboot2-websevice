package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.service.post.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Logger;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final Logger logger;
    @GetMapping("/")
    public String index(Model model) {
        // 머스태치 스타터 덕에, 파일 경로와 확장자(.mustache)는 명시하지 않아도 된다!
        model.addAttribute("posts", postsService.findAllDesc());
        logger.info(model.toString());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
