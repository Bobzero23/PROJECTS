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
            @RequestBody Post post, @RequestParam MultipartFile image)
    throws IOException {
        Post createdPost = postService.createPost(post, image);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(
            @RequestBody Post updatedPost,
            @PathVariable long id,
            @RequestParam(value = "image", required = false) MultipartFile image
    ) throws IOException{
        Post updatedPostResult = postService.updatePost(id, updatedPost, image);
        if (updatedPostResult != null) {
            return ResponseEntity.ok(updatedPostResult);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
