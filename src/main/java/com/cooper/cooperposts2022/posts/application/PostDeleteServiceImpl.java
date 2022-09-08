package com.cooper.cooperposts2022.posts.application;

import com.cooper.cooperposts2022.logging.annotation.DebugRequired;
import com.cooper.cooperposts2022.posts.domain.Post;
import com.cooper.cooperposts2022.posts.repository.PostRepository;
import com.cooper.cooperposts2022.posts.exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostDeleteServiceImpl implements PostDeleteService {

    private final PostRepository postRepository;

    @Override
    @DebugRequired
    public void deletePost(String postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));
        postRepository.delete(post);
    }

}