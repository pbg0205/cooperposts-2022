package com.cooper.cooperposts2022.posts.application;

import com.cooper.cooperposts2022.posts.dto.PostLookupResponseDto;

public interface PostLookupService {

    PostLookupResponseDto findById(String postId);

}
