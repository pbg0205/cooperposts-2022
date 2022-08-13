package com.cooper.cooperposts2022.posts.application;

import com.cooper.cooperposts2022.logging.annotation.DebugRequired;
import com.cooper.cooperposts2022.posts.domain.Post;
import com.cooper.cooperposts2022.posts.domain.PostRepository;
import com.cooper.cooperposts2022.posts.dto.PostLookupResponseDto;
import com.cooper.cooperposts2022.posts.exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostLookupServiceImpl implements PostLookupService {

    private final PostRepository postRepository;

    @Override
    @DebugRequired
    @Cacheable(cacheNames = "posts", key = "#postId")
    public PostLookupResponseDto findById(String postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));
        return PostLookupResponseDto.fromEntity(post);
    }

}
