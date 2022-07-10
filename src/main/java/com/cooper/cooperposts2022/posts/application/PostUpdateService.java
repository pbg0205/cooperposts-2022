package com.cooper.cooperposts2022.posts.application;

import com.cooper.cooperposts2022.posts.dto.PostUpdateRequestDto;
import com.cooper.cooperposts2022.posts.dto.PostUpdateResponseDto;

public interface PostUpdateService {
    PostUpdateResponseDto updatePost(String postId, PostUpdateRequestDto postUpdateRequestDto);
}
