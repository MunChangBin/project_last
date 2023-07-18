package com.example.test11.Article;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final UserService userService;

    @GetMapping("/article/list")
    public String list(Model model){
        List<Article> articleList = this.articleService.getList();
        model.addAttribute("articleList",articleList);
        return "article_list";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/article/create")
    public String create(){
        return "article_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/article/create")
    public String create(@Valid ArticleForm articleForm , BindingResult bindingResult, Principal principal){
        if (bindingResult.hasErrors()) {
            return"article_form";
        }
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.articleService.create(articleForm.getTitle(),articleForm.getContent(),siteUser);
        return "redirect:/article/list";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/article/detail/{id}")
    public String detail(Model model,@PathVariable("id")Integer id){
         Article article = this.articleService.getArticle(id);
         model.addAttribute("article",article);
        return "article_detail";
    }

}
