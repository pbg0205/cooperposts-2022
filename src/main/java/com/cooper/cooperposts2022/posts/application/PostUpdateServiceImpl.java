package com.cooper.cooperposts2022.posts.application;

import com.cooper.cooperposts2022.logging.annotation.DebugRequired;
import com.cooper.cooperposts2022.posts.domain.Post;
import com.cooper.cooperposts2022.posts.domain.PostRepository;
import com.cooper.cooperposts2022.posts.dto.PostUpdateRequestDto;
import com.cooper.cooperposts2022.posts.dto.PostUpdateResponseDto;
import com.cooper.cooperposts2022.posts.exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostUpdateServiceImpl implements PostUpdateService {

    private final PostRepository postRepository;

    @Override
    @DebugRequired
    public PostUpdateResponseDto updatePost(String postId, PostUpdateRequestDto postUpdateRequestDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));
        post.updatePost(postUpdateRequestDto);
        return PostUpdateResponseDto.toEntity(post);
    }

}
