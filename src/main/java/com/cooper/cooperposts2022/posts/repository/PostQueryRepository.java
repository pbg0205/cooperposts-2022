package com.cooper.cooperposts2022.posts.repository;

import com.cooper.cooperposts2022.posts.domain.Post;

import java.util.Optional;

public interface PostQueryRepository {

    Optional<Post> findById(String postId);
}