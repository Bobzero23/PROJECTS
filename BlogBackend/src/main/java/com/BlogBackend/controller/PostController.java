package com.BlogBackend.controller;

import com.BlogBackend.model.Post;
import com.BlogBackend.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;


    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable long id) {
        Post post  = postService.getPostById(id);

        if(post != null ) {
            return ResponseEntity.ok(post);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Post> createPost(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestPart("image") MultipartFile image
    ) throws IOException {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);

        Post createdPost = postService.createPost(post, image);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @PathVariable long id,
            @RequestParam(value = "image", required = false) MultipartFile image
    ) throws IOException {
        Post updatedPost = new Post();
        updatedPost.setTitle(title);
        updatedPost.setContent(content);

        Post updatedPostResult = postService.updatePost(id, updatedPost, image);

        if (updatedPostResult != null) {
            return ResponseEntity.ok(updatedPostResult);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id) {
        Post post = postService.getPostById(id);
        if(post != null) {
            postService.deletePost(id);
            return ResponseEntity.status(HttpStatus.OK).body("Post with id " + id + " deleted successfully");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post with id " + id + " is not valid");
        }
    }
}