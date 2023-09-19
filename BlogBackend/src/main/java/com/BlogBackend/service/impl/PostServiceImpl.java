package com.BlogBackend.service.impl;

import com.BlogBackend.model.Post;
import com.BlogBackend.repository.PostRepository;
import com.BlogBackend.service.PostService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public Post createPost(Post post, MultipartFile multipartFile) throws IOException {
        post.setImage(multipartFile.getBytes());
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(long id, Post updatedPost, MultipartFile newImage) throws IOException {
        Post existingPost = postRepository.findById(id).orElse(null);

        if(existingPost != null) {
            existingPost.setTitle(updatedPost.getTitle());
            existingPost.setContent(updatedPost.getContent());

            if(newImage != null) {
                existingPost.setImage(newImage.getBytes());
            }

        }else {
            return null;
        }

        return postRepository.save(existingPost);
    }

    @Override
    public void deletePost(long id) {
        postRepository.deleteById(id);
    }
}
