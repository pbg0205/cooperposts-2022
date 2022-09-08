package com.cooper.cooperposts2022.posts.repository;

import com.cooper.cooperposts2022.posts.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, String> {
}