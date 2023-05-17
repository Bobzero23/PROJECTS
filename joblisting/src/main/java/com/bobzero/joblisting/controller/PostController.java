package com.bobzero.joblisting.controller;


import com.bobzero.joblisting.model.Post;
import com.bobzero.joblisting.repository.PostRepository;
import com.bobzero.joblisting.repository.SearchRepository;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class PostController{

    private final PostRepository postRepository;
    private final SearchRepository searchRepository;

    public PostController(PostRepository postRepository, SearchRepository searchRepository) {
        this.postRepository = postRepository;
        this.searchRepository = searchRepository;
    }

    @ApiIgnore
    @RequestMapping(value= {"/", "/home"})
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("/posts")
    public List<Post> getAllPost(){
        return postRepository.findAll();
    }

    @PostMapping("/posts/{text}")
    public List<Post> search(@PathVariable String text) {
        return searchRepository.findByText(text);
    }

    @PostMapping("/post")
    public Post addPost(@RequestBody Post post) {
        return postRepository.save(post);
    }
}