package com.cooper.cooperposts2022.posts.domain;

import java.util.Optional;

public interface PostQueryRepository {

    Optional<Post> findById(String postId);
}
