package com.cooper.cooperposts2022.posts.application;

import com.cooper.cooperposts2022.posts.dto.PostCreateRequestDto;
import com.cooper.cooperposts2022.posts.dto.PostCreateResponseDto;
import com.cooper.cooperposts2022.posts.dto.PostLookupResponseDto;

public interface PostService {

    PostCreateResponseDto createPost(PostCreateRequestDto postCreateRequestDto);

    PostLookupResponseDto findById(String postId);
}
