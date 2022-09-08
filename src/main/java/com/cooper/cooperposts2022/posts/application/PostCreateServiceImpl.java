package com.cooper.cooperposts2022.posts.application;

import com.cooper.cooperposts2022.logging.annotation.DebugRequired;
import com.cooper.cooperposts2022.posts.domain.Post;
import com.cooper.cooperposts2022.posts.repository.PostRepository;
import com.cooper.cooperposts2022.posts.dto.PostCreateRequestDto;
import com.cooper.cooperposts2022.posts.dto.PostCreateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostCreateServiceImpl implements PostCreateService {

    private final PostRepository postRepository;

    @Override
    @DebugRequired
    public PostCreateResponseDto createPost(PostCreateRequestDto postCreateRequestDto) {
        Post post = postRepository.save(postCreateRequestDto.toEntity());
        return PostCreateResponseDto.fromEntity(post);
    }

}