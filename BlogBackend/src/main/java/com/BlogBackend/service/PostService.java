package com.BlogBackend.service;

import com.BlogBackend.model.Post;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    Post getPostById(long id);
    Post createPost(Post post, MultipartFile multipartFile) throws IOException;
    Post updatePost(long id, Post post, MultipartFile multipartFile) throws IOException;
    void deletePost(long id);
}
