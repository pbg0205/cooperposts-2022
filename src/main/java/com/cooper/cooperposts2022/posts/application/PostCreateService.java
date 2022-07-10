package com.cooper.cooperposts2022.posts.application;

import com.cooper.cooperposts2022.posts.dto.PostCreateRequestDto;
import com.cooper.cooperposts2022.posts.dto.PostCreateResponseDto;

public interface PostCreateService {

    PostCreateResponseDto createPost(PostCreateRequestDto postCreateRequestDto);

}
