package com.cooper.cooperposts2022.posts.ui;

import com.cooper.cooperposts2022.common.ApiResult;
import com.cooper.cooperposts2022.posts.application.PostDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostDeleteController {

    private final PostDeleteService postDeleteService;

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResult<Void>> deletePost(@PathVariable String postId) {
        postDeleteService.deletePost(postId);
        ApiResult<Void> apiResult = ApiResult.success(null, HttpStatus.OK);
        return ResponseEntity.ok(apiResult);
    }

}
