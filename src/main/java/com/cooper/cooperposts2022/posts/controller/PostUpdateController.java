package com.cooper.cooperposts2022.posts.controller;

import com.cooper.cooperposts2022.common.ApiResult;
import com.cooper.cooperposts2022.posts.application.PostUpdateService;
import com.cooper.cooperposts2022.posts.dto.PostUpdateRequestDto;
import com.cooper.cooperposts2022.posts.dto.PostUpdateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostUpdateController {

    private final PostUpdateService postUpdateService;

    @PutMapping("/{postId}")
    public ResponseEntity<ApiResult<PostUpdateResponseDto>> updatePost(
            @PathVariable String postId,
            @Valid @RequestBody PostUpdateRequestDto postUpdateRequestDto
    ) {
        PostUpdateResponseDto postUpdateResponseDto = postUpdateService.updatePost(postId, postUpdateRequestDto);
        ApiResult<PostUpdateResponseDto> apiResult = ApiResult.success(postUpdateResponseDto, HttpStatus.OK);
        return ResponseEntity.ok(apiResult);
    }

}