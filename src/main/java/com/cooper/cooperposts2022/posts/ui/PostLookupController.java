package com.cooper.cooperposts2022.posts.ui;

import com.cooper.cooperposts2022.common.ApiResult;
import com.cooper.cooperposts2022.posts.application.PostLookupService;
import com.cooper.cooperposts2022.posts.dto.PostLookupResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostLookupController {

    private final PostLookupService postLookupService;

    @GetMapping("/{postId}")
    public ResponseEntity<ApiResult<PostLookupResponseDto>> findById(@PathVariable String postId) {
        PostLookupResponseDto postLookupResponseDto = postLookupService.findById(postId);
        ApiResult<PostLookupResponseDto> apiResult = ApiResult.success(postLookupResponseDto, HttpStatus.OK);
        return ResponseEntity.ok(apiResult);
    }

}
