package com.cooper.cooperposts2022.posts.ui;

import com.cooper.cooperposts2022.common.ApiResult;
import com.cooper.cooperposts2022.posts.application.PostService;
import com.cooper.cooperposts2022.posts.dto.PostCreateRequestDto;
import com.cooper.cooperposts2022.posts.dto.PostCreateResponseDto;
import com.cooper.cooperposts2022.posts.dto.PostLookupResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<ApiResult<PostCreateResponseDto>> createPost(@RequestBody PostCreateRequestDto postCreateRequestDto) {
        PostCreateResponseDto postCreateResponseDto = postService.createPost(postCreateRequestDto);
        ApiResult<PostCreateResponseDto> apiResult = ApiResult.success(postCreateResponseDto, HttpStatus.OK);
        return ResponseEntity.ok(apiResult);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ApiResult<PostLookupResponseDto>> findById(@PathVariable String postId) {
        PostLookupResponseDto postLookupResponseDto = postService.findById(postId);
        ApiResult<PostLookupResponseDto> apiResult = ApiResult.success(postLookupResponseDto, HttpStatus.OK);
        return ResponseEntity.ok(apiResult);
    }

}

