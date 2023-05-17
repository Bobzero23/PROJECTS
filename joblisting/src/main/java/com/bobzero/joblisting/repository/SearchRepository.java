package com.bobzero.joblisting.repository;

import com.bobzero.joblisting.model.Post;
import org.springframework.stereotype.Component;

import java.util.List;

public interface SearchRepository {
    List<Post> findByText(String text);
}
